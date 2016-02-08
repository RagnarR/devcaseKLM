package com.klm.dev.exercises.devcase01;

import com.klm.dev.exercises.devcase01.bo.ApplicationInfo;
import com.klm.dev.exercises.devcase01.bo.ApplicationInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static Logger LOGGER = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private ApplicationInfoManager applicationInfoManager;

    @RequestMapping(value = "/app-info")
    public ApplicationInfo applicationInfoImpl() {
        try {
            return applicationInfoManager.load();
        } catch (Throwable e) {
            LOGGER.error("Error while loading Application Info;", e);
        }

        return null;
    }

}
