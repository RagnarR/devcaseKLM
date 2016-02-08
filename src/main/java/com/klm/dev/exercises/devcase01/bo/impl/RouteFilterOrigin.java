package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.model.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteFilterOrigin {

    public List<Route> filter(List<Route> routes, String origin) {
        List<Route> returnList = new ArrayList<>();

        for (Route route : routes) {
            if (route.getOrigin().equals(origin)) {
                returnList.add(route);
            }
        }
        return returnList;
    }

}
