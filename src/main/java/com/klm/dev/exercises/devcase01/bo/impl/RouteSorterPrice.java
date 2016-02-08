package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.bo.DestinationListSorter;
import com.klm.dev.exercises.devcase01.model.Fare;
import com.klm.dev.exercises.devcase01.model.Route;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component("destinationListSorterByPrice")
public class RouteSorterPrice implements DestinationListSorter {

    @Override
    public List<Route> sort(List<Route> routes) {
        Collections.sort(routes, new Comparator<Route>() {

            @Override
            public int compare(Route o1, Route o2) {
                Fare lowestFare1 = o1.getLowestFare();
                Double valueRoute1 = lowestFare1.getValue();
                Fare lowestFare2 = o2.getLowestFare();
                Double valueRoute2 = lowestFare2.getValue();
                return valueRoute1.compareTo(valueRoute2);
            }
        });
        return routes;
    }

}
