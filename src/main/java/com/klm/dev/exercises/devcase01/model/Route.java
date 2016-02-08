package com.klm.dev.exercises.devcase01.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Route {

    private String origin;
    private Airport destination;
    private Fare lowestFare;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Fare getLowestFare() {
        return lowestFare;
    }

    public void setLowestFare(Fare lowestFare) {
        this.lowestFare = lowestFare;
    }

    @Override
    public String toString() {
        return "Route{" +
                "origin='" + origin + '\'' +
                ", destination=" + destination +
                ", lowestFare=" + lowestFare +
                '}';
    }
}
