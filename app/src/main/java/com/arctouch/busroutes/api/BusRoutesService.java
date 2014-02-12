package com.arctouch.busroutes.api;

import java.util.List;
import com.arctouch.busroutes.model.*;

import retrofit.Callback;
import retrofit.http.*;

/**
 * Created by Wagner on 10/02/14.
 */
public interface BusRoutesService {
    @Headers({
            "Authorization: Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==",
            "X-AppGlu-Environment: staging"
    })
    @POST("/v1/queries/findRoutesByStopName/run")
    void findRoutesByStopName(@Body Stop stop, Callback<List<BusRoute>> cb);
}
