package com.arctouch.busroutes.model;

/**
 * BusRoute model class
 * Filled by the ServiceApi and consumed by the Activities
 * Properties follow the service JSON response
 */
public class BusRoute {
    public int id;
    public String shortName;
    public String longName;
    public String lastModifiedDate;
    public int agencyId;

    public String toString() {
        return longName;
    }
}
