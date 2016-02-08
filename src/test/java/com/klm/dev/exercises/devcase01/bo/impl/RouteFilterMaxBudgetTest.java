package com.klm.dev.exercises.devcase01.bo.impl;

import com.klm.dev.exercises.devcase01.model.Fare;
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
public class RouteFilterMaxBudgetTest {

    @Test
    public void filter() {
        RouteFilterMaxBudget routeFilter = new RouteFilterMaxBudget();
        List<Route> testList = new ArrayList<>();
        Route route1 = new Route();
        Fare fare1 = new Fare();
        fare1.setCurrency("EUR");
        fare1.setValue(100.0);
        route1.setLowestFare(fare1);


        testList.add(route1);

        Assert.assertEquals(testList.size(), 1);

        testList = routeFilter.filter(testList, 100.0);

        Assert.assertEquals(testList.size(), 1);

        testList = routeFilter.filter(testList, 50.0);

        Assert.assertEquals(testList.size(), 0);

    }

}
