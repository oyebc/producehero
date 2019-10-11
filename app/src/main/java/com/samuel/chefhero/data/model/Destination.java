package com.samuel.chefhero.data.model;

import java.util.List;

public class Destination {
    List<OrderItem> orders;
    String address;
    String destinationName;

    public Destination(List<OrderItem> orders, String address) {
        this.orders = orders;
        this.address = address;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public String getAddress() {
        return address;
    }

    public String getDestinationName() {
        return destinationName;
    }
}
