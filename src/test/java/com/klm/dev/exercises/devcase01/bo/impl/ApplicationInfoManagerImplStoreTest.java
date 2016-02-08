package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.bo.ApplicationInfo;
import com.klm.dev.exercises.devcase01.bo.ApplicationInfoManager;
import com.klm.dev.exercises.devcase01.config.ApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {ApplicationConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ApplicationInfoManagerImplStoreTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationInfoManager applicationInfoManager;

    @BeforeMethod
    private void cleanup() throws IOException {
        ((ApplicationInfoManagerImpl) applicationInfoManager).setApplicationInfoStorage(new FileSystemResource(
                "./src/test/resources/application-storage.txt"));
        ((ApplicationInfoManagerImpl) applicationInfoManager).getApplicationInfoStorage().getFile().delete();
    }

    @Test
    @DirtiesContext
    public void storeApplicationInfo() throws Throwable {


        ApplicationInfo applicationInfo = applicationInfoManager.updateStatistics(333L);

        Assert.assertNotNull(applicationInfo);
        Assert.assertEquals(applicationInfo.getAverageRequestProcessingTime(), Long.valueOf(333L));

        applicationInfoManager.storeApplicationInfo();

        List<String> readAllLines = Files.readAllLines(((ApplicationInfoManagerImpl) applicationInfoManager).getApplicationInfoStorage()
                .getFile().toPath(), Charset.defaultCharset());

        Assert.assertEquals(readAllLines.get(0), "1;333");

        ((ApplicationInfoManagerImpl) applicationInfoManager).getApplicationInfoStorage().getFile().delete();
    }
}
