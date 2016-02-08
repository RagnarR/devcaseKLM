package com.klm.dev.exercises.devcase01.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DestinationList {

    private List<Route> destinations;

    public List<Route> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Route> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "DestinationList{" +
                "destinations=" + destinations +
                '}';
    }
}
