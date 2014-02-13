package com.arctouch.busroutes.api;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Wagner on 10/02/14.
 */
public interface BusRoutesServiceApi {
    @POST("/v1/queries/findRoutesByStopName/run")
    void findRoutesByStopName(@Body FindRoutesByStopNameParams params, Callback<FindRoutesByStopNameResponse> cb);

    @POST("/v1/queries/findDeparturesByRouteId/run")
    void findDeparturesByRouteId(@Body FindDeparturesByRouteIdParams params, Callback<FindDeparturesByRouteIdResponse> cb);
}
