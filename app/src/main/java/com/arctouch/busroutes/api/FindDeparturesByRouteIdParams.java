package com.arctouch.busroutes.api;

/**
 * Mapping of the findDeparturesByRouteId request parameters
 * The remote service expects the JSON object to be enveloped in a "params" object
 */
public class FindDeparturesByRouteIdParams {

    public class FindDeparturesByRouteIdParamsData {
        int routeId;
    }

    public FindDeparturesByRouteIdParamsData params;

    public FindDeparturesByRouteIdParams(int routeId) {
        params = new FindDeparturesByRouteIdParamsData();
        params.routeId = routeId;
    }
}
