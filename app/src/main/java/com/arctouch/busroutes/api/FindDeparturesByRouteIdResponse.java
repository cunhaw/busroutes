package com.arctouch.busroutes.api;

import com.arctouch.busroutes.model.Departure;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Mapping of the findDeparturesByRouteId request response
 * The rowsAffected response is not being used
 */

/* Sample JSON response mapped for this response:
  {
   "rows":
    [
       {"id":208,"calendar":"WEEKDAY","time":"05:59"},
       {"id":8,"calendar":"WEEKDAY","time":"14:11"},
       {"id":228,"calendar":"WEEKDAY","time":"14:49"},
       {"id":246,"calendar":"SATURDAY","time":"07:03"},
       {"id":247,"calendar":"SATURDAY","time":"07:28"},
       {"id":266,"calendar":"SATURDAY","time":"23:00"},
       {"id":267,"calendar":"SUNDAY","time":"07:56"},
       {"id":279,"calendar":"SUNDAY","time":"23:25"}
     ],
     "rowsAffected":0
  }
 */

public class FindDeparturesByRouteIdResponse {
    @SerializedName("rows")
    public List<Departure> departures;
}
