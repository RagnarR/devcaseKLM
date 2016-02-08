package com.klm.dev.exercises.devcase01.service.impl;

import com.klm.dev.exercises.devcase01.bo.DestinationListSorter;
import com.klm.dev.exercises.devcase01.bo.impl.RouteFilterMinBudget;
import com.klm.dev.exercises.devcase01.model.DestinationList;
import com.klm.dev.exercises.devcase01.model.Route;
import com.klm.dev.exercises.devcase01.service.DestinationService;
import com.klm.dev.exercises.devcase01.utils.RemoteCallUtil;
import com.klm.dev.exercises.devcase01.view.DestinationFinderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class DestinationServiceImpl implements DestinationService {

    private static Logger LOGGER = LoggerFactory.getLogger(DestinationServiceImpl.class);

    @Autowired
    private Environment env;

    @Autowired
    private RouteFilterMinBudget routeFilterMinBudget;

    @Autowired
    @Qualifier("destinationListSorterByPrice")
    private DestinationListSorter priceDestinationListSorter;

    @Autowired
    @Qualifier("destinationListSorterByName")
    private DestinationListSorter nameDestinationListSorter;

    private RestTemplate restTemplate = new RestTemplate();

    @Async
    @Override
    public Future<List<Route>> retrieveDestinations(DestinationFinderForm destinationFinderForm) {

        Map<String, String> params = prepareServiceParams(destinationFinderForm);

        LOGGER.debug("retrieveDestinations; params: {}", params);

        DestinationList destinationList = restTemplate.getForObject(
                RemoteCallUtil.buildUriWithParams(env.getProperty("service.df.endpoint"), params), DestinationList.class, params
        );

        List<Route> routes = filterRoutes(destinationFinderForm, destinationList);

        routes = sortRoutes(destinationFinderForm, routes);

        return new AsyncResult<>(routes);
    }


    private List<Route> sortRoutes(DestinationFinderForm destinationFinderForm, List<Route> routes) {
        DestinationListSorter destinationSorter =
                destinationFinderForm.getSortByPrice() ? priceDestinationListSorter : nameDestinationListSorter;

        routes = destinationSorter.sort(routes);
        return routes;
    }


    private List<Route> filterRoutes(DestinationFinderForm destinationFinderForm, DestinationList destinationList) {
        List<Route> routes = destinationList.getDestinations();

        if (destinationFinderForm.getMinBudget() != null && !destinationFinderForm.getMinBudget().isNaN()) {
            routes = routeFilterMinBudget.filter(routes, destinationFinderForm.getMinBudget());
        }
        return routes;
    }


    private Map<String, String> prepareServiceParams(DestinationFinderForm destinationFinderForm) {
        Map<String, String> params = new HashMap<>();

        params.put("origin", destinationFinderForm.getOrigin());
        params.put("pos", destinationFinderForm.getPos());
        params.put("maxBudget", destinationFinderForm.getMaxBudget().toString());
        return params;
    }
}
