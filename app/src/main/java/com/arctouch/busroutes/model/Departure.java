package com.arctouch.busroutes.model;

/**
 * Departure model class
 * Filled by the ServiceApi and consumed by the Activities
 * Properties follow the service JSON response
 */
public class Departure {
    public int id;
    public String calendar;
    public String time;

    public String toString() {
        return new StringBuilder().append(id).append(" ").append(time).toString();
    }
}
