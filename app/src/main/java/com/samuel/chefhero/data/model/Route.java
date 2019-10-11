package com.samuel.chefhero.data.model;

import java.util.List;

public class Route {
    List<Destination> destinations;
    String routeName;

    public Route(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public String getRouteName() {
        return routeName;
    }
}
