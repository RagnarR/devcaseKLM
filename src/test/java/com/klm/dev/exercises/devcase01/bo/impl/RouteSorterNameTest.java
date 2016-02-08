package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.config.ApplicationConfiguration;
import com.klm.dev.exercises.devcase01.model.Airport;
import com.klm.dev.exercises.devcase01.model.Route;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RouteSorterNameTest {

    @Test
    public void sort() {
        RouteSorterName routeSorter = new RouteSorterName();
        List<Route> testList = new ArrayList<>();
        Route route1 = new Route();
        Airport airport1 = new Airport();
        airport1.setName("AMS");
        route1.setDestination(airport1);
        testList.add(route1);

        Route route2 = new Route();
        Airport airport2 = new Airport();
        airport2.setName("MIL");
        route2.setDestination(airport2);
        testList.add(route2);

        Route route3 = new Route();
        Airport airport3 = new Airport();
        airport3.setName("BCN");
        route3.setDestination(airport3);
        testList.add(route3);

        testList = routeSorter.sort(testList);

        Assert.assertEquals(testList.get(0).getDestination().getName(), "AMS");
        Assert.assertEquals(testList.get(1).getDestination().getName(), "BCN");
        Assert.assertEquals(testList.get(2).getDestination().getName(), "MIL");


        Assert.assertEquals(testList.size(), 3);
    }

}
