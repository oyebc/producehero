package com.samuel.chefhero.data.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    List<Destination> destinations;
    String routeName;

    public Route(List<Destination> destinations, String routeName) {
        this.destinations = destinations;
        this.routeName = routeName;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public String getRouteName() {
        return routeName;
    }
}
