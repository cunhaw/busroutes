package com.arctouch.busroutes.api;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * This class instantiates and holds a single instance of {@link BusRoutesServiceApi} for the App.
 * Depends on the Retrofit library / RestAdapter to create the Api implementation.
 * The concrete implementation of the Api is built by the Retrofit RestAdapter.
 */

public class BusRoutesService {
    private static BusRoutesServiceApi service;

    public BusRoutesService() {

        // TODO: Move headers to configuration file
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", "Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==");
                request.addHeader("X-AppGlu-Environment", "staging");
            }
        };

        // TODO: Move server address to configuration file
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
