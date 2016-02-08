package com.klm.dev.exercises.devcase01.bo;

import com.klm.dev.exercises.devcase01.model.Route;

import java.util.List;

public interface DestinationListSorter {

    /**
     * Sorts the given list of routes
     *
     * @param routes unsorted list
     * @return the sorted list of routes
     */
    List<Route> sort(List<Route> routes);
}