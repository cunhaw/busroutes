package com.arctouch.busroutes.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arctouch.busroutes.R;
import com.arctouch.busroutes.api.BusRoutesService;
import com.arctouch.busroutes.api.FindDeparturesByRouteIdParams;
import com.arctouch.busroutes.api.FindDeparturesByRouteIdResponse;
import com.arctouch.busroutes.model.Departure;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A fragment representing a single Route detail screen.
 * This fragment is either contained in a {@link RouteListActivity}
 * in two-pane mode (on tablets) or a {@link RouteDetailActivity}
 * on handsets.
 */
public class RouteDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The Bus route information this fragment is presenting.
     */
    // private BusRoute mBusRoute;
    // private List<Departure> mDepartures;

    /**
     * The activity parameter (Route id).
     */
    String mRouteIdParam;

    /**
     * The fragment UI object references.
     */
    private View mRootView;
    private TextView mTextView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RouteDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mRouteIdParam = getArguments().getString(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_route_detail, container, false);
        mTextView = ((TextView) mRootView.findViewById(R.id.route_detail));

        // Set the loading message in the fragment TextView.
        String loadingMessage = getResources().getString(R.string.loading_bus_route_message);
        mTextView.setText(loadingMessage);

        // Request the bus route information to the external service.
        // TODO: Investigate the Loader approach to load content from a content provider
        if (mRouteIdParam != null) {
            int routeId = Integer.parseInt(mRouteIdParam);
            requestBusRouteDetails(routeId);
        }

        return mRootView;
    }

    private void requestBusRouteDetails(int routeId) {

        // TODO: Decouple Activity from Retrofit library

        BusRoutesService.api().findDeparturesByRouteId(
                new FindDeparturesByRouteIdParams(routeId),
                new Callback<FindDeparturesByRouteIdResponse>() {

                    @Override
                    public void success(FindDeparturesByRouteIdResponse departuresResponse, Response response) {
                        renderDepartures(departuresResponse.departures);
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        String errorRetrievingBusRoutes = getResources().getString(R.string.error_retrieving_bus_routes_message);
                        Toast toast = Toast.makeText(getActivity(), errorRetrievingBusRoutes, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

        );
    }

    public void renderDepartures(List<Departure> departures) {

        // TODO: Move this to an ListView + CustomAdapter instead of a TextView

        String routesDetailHeader = getResources().getString(R.string.routes_detail_header);
        mTextView.setText(routesDetailHeader);
        mTextView.append("\n");

        // Iterate trough the departures adding them to the TextView
        String currentCalendar = "";

        for (Departure departure : departures) {

            // Split departures for different calendars (WEEKDAY, SATURDAY, SUNDAY)
            if (!currentCalendar.equals(departure.calendar)) {
                currentCalendar = departure.calendar;
                mTextView.append("\n");
                mTextView.append(departure.calendar.concat("\n"));
            }

            mTextView.append(departure.time.concat("\n"));
        }
    }
}
