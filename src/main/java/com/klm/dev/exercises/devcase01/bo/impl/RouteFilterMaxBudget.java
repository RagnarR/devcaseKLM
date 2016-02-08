package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.model.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteFilterMaxBudget {

    public List<Route> filter(List<Route> routes, Double maxBudget) {
        List<Route> returnList = new ArrayList<>();

        for (Route route : routes) {
            if (route.getLowestFare().getValue().compareTo(maxBudget) <= 0) {
                returnList.add(route);
            }
        }
        return returnList;
    }

}
