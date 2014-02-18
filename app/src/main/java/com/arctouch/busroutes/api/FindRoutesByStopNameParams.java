package com.arctouch.busroutes.api;

/**
 * Mapping of the findRoutesByStopName request parameters
 * The remote service expects the JSON object to be enveloped in a "params" object
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

