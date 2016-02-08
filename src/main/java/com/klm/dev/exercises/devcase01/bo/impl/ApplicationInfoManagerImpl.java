package com.klm.dev.exercises.devcase01.bo.impl;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.klm.dev.exercises.devcase01.bo.ApplicationInfoManager;
import com.klm.dev.exercises.devcase01.bo.ApplicationInfo;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInfoManagerImpl implements ApplicationInfoManager {
    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationInfoManagerImpl.class);

    @Autowired
    private Environment env;

    private Resource applicationInfoStorage;
    private String applicationVersion;
    private final ApplicationInfoImpl applicationInfo = new ApplicationInfoImpl();
    private boolean initialized = false;
    private final Object fileLock = new Object();
    private final Object updateLock = new Object();

    public ApplicationInfoManagerImpl() {
        super();
    }

    @PostConstruct
    public void init() {
        applicationInfoStorage =  new ClassPathResource(env.getProperty(APP_STORAGE_LOCATION));
        applicationVersion = env.getProperty(APP_VERSION);
        try {
            load();
        } catch (Throwable ex) {
            //log and throw unchecked exception to stop app starting
            LOGGER.error(MSG_CANT_INIT_APP, ex);
            throw new RuntimeException(MSG_CANT_INIT_APP);
        }
    }

    @Override
    public ApplicationInfo load() throws Throwable {

        if (!initialized) {
            synchronized (fileLock) {

                if (!initialized) {

                    if (!applicationInfoStorage.exists()) {
                        initApplicationInfoStatistics(new AtomicLong(0L), 0L);
                        LOGGER.debug("Create new storage file!");
                    } else {

                        List<String> lines = Files.readAllLines(applicationInfoStorage.getFile().toPath(), Charset.defaultCharset());
                        LOGGER.debug("ApplicationInfo loaded values: {}", lines);

                        StringTokenizer tokenizer = new StringTokenizer(lines.get(0), STRING_DELIMITER);

                        initApplicationInfoStatistics(new AtomicLong(Long.parseLong(tokenizer.nextToken())), Long.parseLong(tokenizer.nextToken()));
                    }

                    applicationInfo.setVersion(applicationVersion);

                    initialized = true;
                }
            }
        }
        return new ApplicationInfoImpl(applicationInfo);
    }

    private void initApplicationInfoStatistics(AtomicLong requestCount, long averageRequestProcessingTime) {
        applicationInfo.setRequestCount(requestCount);
        applicationInfo.setAverageRequestProcessingTime(averageRequestProcessingTime);
    }

    @Override
    public ApplicationInfo updateStatistics(Long newRequestProcessingTime) {


        synchronized (updateLock) {
            long averageRequestProcessingValue = applicationInfo.getAverageRequestProcessingTime();
            long requestCount = applicationInfo.getRequestCount().longValue();

            // Weighted average
            long newAverageRequestProcessingTime = (averageRequestProcessingValue * requestCount + newRequestProcessingTime)
                    / (requestCount + 1l);

            applicationInfo.setAverageRequestProcessingTime(newAverageRequestProcessingTime);
        }
        applicationInfo.increaseRequestCount();
        return new ApplicationInfoImpl(applicationInfo);
    }

    @Override
    public void storeApplicationInfo() throws Throwable {
        Path path = applicationInfoStorage.getFile().toPath();
        byte[] bytes = applicationInfo.toPersistString().getBytes();

        // Even if it's unlikely to happen, we want to prevent any possible
        // writing while reading/loading the information or the other way around
        synchronized (fileLock) {
            Files.write(path, bytes);
        }
    }

    /**
     * @return the initialized
     */
    public Boolean getInitialized() {
        return initialized;
    }

    private class ApplicationInfoImpl implements ApplicationInfo {

        private static final double NANOSECONDS_TO_SECONDS = 1000000000.0;

        public ApplicationInfoImpl() {
            super();
        }

        public ApplicationInfoImpl(ApplicationInfo applicationInfo) {
            super();
            this.averageRequestProcessingTime = applicationInfo.getAverageRequestProcessingTime();
            this.requestCount = applicationInfo.getRequestCount();
            this.version = applicationInfo.getVersion();
        }

        private String version;
        private AtomicLong requestCount;
        private Long averageRequestProcessingTime;

        @Override
        public void increaseRequestCount() {
            requestCount.incrementAndGet();
        }

        @Override
        public String getVersion() {
            return version;
        }

        /**
         * @param version the version to set
         */
        public void setVersion(String version) {
            this.version = version;
        }


        @Override
        public AtomicLong getRequestCount() {
            return requestCount;
        }

        /**
         * @param requestCount the requestCount to set
         */
        public void setRequestCount(AtomicLong requestCount) {
            this.requestCount = requestCount;
        }

        @Override
        public Long getAverageRequestProcessingTime() {
            return averageRequestProcessingTime;
        }

        /**
         * @param averageRequestProcessingTime the averageRequestProcessingTime to set
         */
        public void setAverageRequestProcessingTime(Long averageRequestProcessingTime) {
            this.averageRequestProcessingTime = averageRequestProcessingTime;
        }

        @Override
        public String toString() {
            return version + STRING_DELIMITER + requestCount + STRING_DELIMITER + averageRequestProcessingTime;
        }

        public String toPersistString() {
            return requestCount + STRING_DELIMITER + averageRequestProcessingTime;
        }

        @Override
        public String getAverageRequestProcessingSeconds() {
            return (getAverageRequestProcessingTime() / NANOSECONDS_TO_SECONDS) + "s";
        }
    }

    /**
     * NOT TO BE USED. JUST An ENTRY POINT FOR TESTING PURPOSE.
     *
     * @return the applicationInfoStorage
     */
    public Resource getApplicationInfoStorage() {
        return applicationInfoStorage;
    }

    public void setApplicationInfoStorage(Resource applicationInfoStorage) {
        this.applicationInfoStorage = applicationInfoStorage;
    }
}
