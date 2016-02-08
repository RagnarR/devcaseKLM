package com.klm.dev.exercises.devcase01.bo;

public interface ApplicationInfoManager {

    String APP_STORAGE_LOCATION = "application.storage.location";
    String APP_VERSION = "application.version";

    String STRING_DELIMITER = ";";

    String MSG_CANT_INIT_APP = "Can\'t init app info!";

    /**
     * Loads the current application information returning a defensive copy of the original object.
     *
     * @return the {@link com.klm.dev.exercises.devcase01.bo.impl.ApplicationInfoManagerImpl.ApplicationInfoImpl}
     * @throws Throwable
     */
    ApplicationInfo load() throws Throwable;

    /**
     * Updates the statistic information related to the average processing time required to process the requests and number of requests.<br>
     * Number of requests are always incremented by one for each call.
     * This method is required to be thread safe.
     *
     * @param newRequestProcessingTime the new processing time required to process a request
     * @return the updated {@link com.klm.dev.exercises.devcase01.bo.impl.ApplicationInfoManagerImpl.ApplicationInfoImpl}. This is a defensive copy of the original object.
     */
    ApplicationInfo updateStatistics(Long newRequestProcessingTime);


    /**
     * Stores the current {@link com.klm.dev.exercises.devcase01.bo.impl.ApplicationInfoManagerImpl.ApplicationInfoImpl} in a persistent storage
     * @throws Throwable
     *
     */
    void storeApplicationInfo() throws Throwable;

}
