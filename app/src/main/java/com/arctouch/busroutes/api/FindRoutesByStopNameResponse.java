package com.arctouch.busroutes.api;

import com.arctouch.busroutes.model.BusRoute;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Mapping of the findRoutesByStopName request response
 * The rowsAffected response is not being used
 */

/* Sample JSON response mapped for this response:
  {
   "rows":
    [
      {"id":22,"shortName":"131","longName":"AGRONÔMICA VIA GAMA D'EÇA","lastModifiedDate":"2009-10-26T02:00:00+0000","agencyId":9},
      {"id":32,"shortName":"133","longName":"AGRONÔMICA VIA MAURO RAMOS","lastModifiedDate":"2012-07-23T03:00:00+0000","agencyId":9}
    ],
   "rowsAffected":0
  }
 */

public class FindRoutesByStopNameResponse {
    @SerializedName("rows")
    public List<BusRoute> routes;
}

