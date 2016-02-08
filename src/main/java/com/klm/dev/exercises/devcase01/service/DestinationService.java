package com.klm.dev.exercises.devcase01.service;

import com.klm.dev.exercises.devcase01.model.Route;
import com.klm.dev.exercises.devcase01.view.DestinationFinderForm;

import java.util.List;
import java.util.concurrent.Future;

public interface DestinationService {

    /**
     * Retrieves the reachable destination according to the given form parameter
     *
     * @param destinationFinderForm the form parameter
     * @return the list of routes from the origin to every reachable destination
     *         according to the given parameters
     */
    Future<List<Route>> retrieveDestinations(DestinationFinderForm destinationFinderForm);

}
