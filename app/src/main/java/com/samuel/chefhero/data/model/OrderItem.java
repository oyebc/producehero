package com.samuel.chefhero.data.model;

public class OrderItem {
    String itemName;
    int weight;
    int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getWeight() {
        return weight;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
