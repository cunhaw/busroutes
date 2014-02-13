package com.arctouch.busroutes.api;

/**
 * Created by Wagner on 13/02/14.
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
