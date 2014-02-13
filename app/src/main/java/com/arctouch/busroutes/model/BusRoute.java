package com.arctouch.busroutes.model;

/**
 * Created by Wagner on 10/02/14.
 */
public class BusRoute {
    public int id;
    public String shortName;
    public String longName;
    public String lastModifiedDate;
    public int agencyId;

    public String toString() {
        return new StringBuilder().append(id).append(":").append(longName).toString();
    }
}
