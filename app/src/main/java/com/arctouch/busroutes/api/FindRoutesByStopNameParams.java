package com.arctouch.busroutes.api;

/**
 * Created by Wagner on 13/02/14.
 */
public class FindRoutesByStopNameParams {

    public class FindRoutesByStopNameParamsData {
        String stopName;
    }

    public FindRoutesByStopNameParamsData params;

    public FindRoutesByStopNameParams(String stopName) {
        params = new FindRoutesByStopNameParamsData();
        params.stopName = stopName;
    }
}

