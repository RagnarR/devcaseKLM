package com.klm.dev.exercises.devcase01.utils;

import java.util.Map;

public class RemoteCallUtil {

    public static String buildUriWithParams(String baseServiceUri, Map<String, String> params) {

        StringBuilder uri = new StringBuilder(baseServiceUri + "?");

        for (String key : params.keySet()) {
            uri.append(key).append("={").append(key).append("}&");
        }

        return uri.substring(0, uri.length() - 1);
    }

}
