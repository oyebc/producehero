package com.samuel.chefhero.data.model;

import java.util.List;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private String userId;
    private String displayName;
    private List<Route> routes;

    public User(String userId, String displayName, List <Route> routes) {
        this.userId = userId;
        this.displayName = displayName;
        this.routes = routes;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
