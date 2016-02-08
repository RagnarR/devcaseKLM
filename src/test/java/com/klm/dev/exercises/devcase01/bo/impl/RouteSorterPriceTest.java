package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.config.ApplicationConfiguration;
import com.klm.dev.exercises.devcase01.model.Fare;
import com.klm.dev.exercises.devcase01.model.Route;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {ApplicationConfiguration.class})
@WebAppConfiguration
public class RouteSorterPriceTest extends AbstractTestNGSpringContextTests {

    @Test
    public void sort() {
        RouteSorterPrice routeSorter = new RouteSorterPrice();
        List<Route> testList = new ArrayList<>();
        Route route1 = new Route();
        Fare fare1 = new Fare();
        fare1.setCurrency("EUR");
        fare1.setValue(10.0);
        route1.setLowestFare(fare1);
        testList.add(route1);

        Route route2 = new Route();
        Fare fare2 = new Fare();
        fare2.setCurrency("EUR");
        fare2.setValue(30.0);
        route2.setLowestFare(fare2);
        testList.add(route2);

        Route route3 = new Route();
        Fare fare3 = new Fare();
        fare3.setCurrency("EUR");
        fare3.setValue(30.0);
        route3.setLowestFare(fare3);
        testList.add(route3);

        testList = routeSorter.sort(testList);

        Assert.assertEquals(testList.get(0).getLowestFare().getValue(), 10.0);
        Assert.assertEquals(testList.get(1).getLowestFare().getValue(), 30.0);
        Assert.assertEquals(testList.get(2).getLowestFare().getValue(), 30.0);
        Assert.assertEquals(testList.get(2).getLowestFare().getCurrency(), "EUR");

        Assert.assertEquals(testList.size(), 3);
    }

}
