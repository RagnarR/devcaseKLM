package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.model.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteFilterMinBudget {

    public List<Route> filter(List<Route> routes, Double minBudget) {
        List<Route> returnList = new ArrayList<>();

        for (Route route : routes) {
            if (route.getLowestFare().getValue().compareTo(minBudget) >= 0) {
                returnList.add(route);
            }
        }
        return returnList;
    }

}