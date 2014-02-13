package com.arctouch.busroutes.model;

/**
 * Created by Wagner on 13/02/14.
 */
public class Departure {
    public int id;
    public String calendar;
    public String time;

    public String toString() {
        return new StringBuilder().append(id).append(" ").append(time).toString();
    }
}
