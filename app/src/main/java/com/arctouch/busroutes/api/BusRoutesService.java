package com.arctouch.busroutes.api;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Wagner on 13/02/14.
 */

public class BusRoutesService {
    private static BusRoutesServiceApi service;

    public BusRoutesService() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==");
                request.addHeader("X-AppGlu-Environment", "staging");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://dashboard.appglu.com")
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(BusRoutesServiceApi.class);
    }

    public static BusRoutesServiceApi api() {
        if (service == null) {
            new BusRoutesService();
        }
        return service;
    }
}
