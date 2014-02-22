package com.arctouch.busroutes.api;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

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

        // Setup a 10 seconds timeout for requests
        OkHttpClient ok = new OkHttpClient();
        ok.setConnectTimeout(10, TimeUnit.SECONDS);

        // TODO: Move server address to configuration file
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://dashboard.appglu.com")
                .setRequestInterceptor(requestInterceptor)
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(ok))
                .build();

        // Build the Api service implementation
        service = restAdapter.create(BusRoutesServiceApi.class);
    }

    public static BusRoutesServiceApi api() {
        if (service == null) {
            new BusRoutesService();
        }
        return service;
    }
}
