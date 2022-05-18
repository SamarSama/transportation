package com.example.transportation.Models;

import java.util.List;

public class RouteModel {
    private String from,to;

    public RouteModel() {
    }

    public RouteModel(String id, String from, String to, List<String> routes) {
        this.from = from;
        this.to = to;
        this.routes = routes;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> routes) {
        this.routes = routes;
    }

    private   List<String> routes;
}
