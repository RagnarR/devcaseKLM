package com.klm.dev.exercises.devcase01.service.impl;

import com.klm.dev.exercises.devcase01.config.ApplicationConfiguration;
import com.klm.dev.exercises.devcase01.model.Route;
import com.klm.dev.exercises.devcase01.service.DestinationService;
import com.klm.dev.exercises.devcase01.view.DestinationFinderForm;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {ApplicationConfiguration.class})
@WebAppConfiguration
public class DestinationServiceImplTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    private DestinationService destinationService;

    @Test
    public void retrieveDestinations() throws ExecutionException, InterruptedException {
        DestinationFinderForm destinationFinderForm = new DestinationFinderForm();

        destinationFinderForm.setMaxBudget(300.0);
        destinationFinderForm.setMinBudget(150.0);
        destinationFinderForm.setOrigin("MSQ");
        destinationFinderForm.setPos("BY");


        Future<List<Route>> futureRoutes = destinationService.retrieveDestinations(destinationFinderForm);
        List<Route> routes = futureRoutes.get();

        Assert.assertEquals(routes.size(), 34);
        Assert.assertEquals(routes.get(0).getDestination().getName(), "Aalborg");
        Assert.assertEquals(routes.get(3).getDestination().getName(), "Bergen");
    }

}
