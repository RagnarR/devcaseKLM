package com.klm.dev.exercises.devcase01.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.concurrent.atomic.AtomicLong;

public interface ApplicationInfo {

    void increaseRequestCount();

    /**
     * @return the version
     */
    String getVersion();

    /**
     * @return the requestCount
     */
    AtomicLong getRequestCount();

    /**
     * @return the averageRequestProcessingTime
     */
    @JsonIgnore
    Long getAverageRequestProcessingTime();

    /**
     * @return the averageRequestProcessingTime
     */
    String getAverageRequestProcessingSeconds();

}
