package com.klm.dev.exercises.devcase01.bo.impl;

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
public class RouteFilterOriginTest {

    @Test
    public void filter() {
        RouteFilterOrigin routeFilter = new RouteFilterOrigin();
        List<Route> testList = new ArrayList<>();
        Route route1 = new Route();
        route1.setOrigin("MIL");


        testList.add(route1);

        Assert.assertEquals(testList.size(), 1);

        testList = routeFilter.filter(testList, "MIL");

        Assert.assertEquals(testList.size(), 1);

        testList = routeFilter.filter(testList, "AMS");

        Assert.assertEquals(testList.size(), 0);
    }

}
