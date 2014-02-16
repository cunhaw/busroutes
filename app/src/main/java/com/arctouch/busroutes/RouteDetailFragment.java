package com.arctouch.busroutes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        // if (getArguments().containsKey(ARG_ITEM_ID)) {
        // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
        // mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        // }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_route_detail, container, false);
        mTextView = ((TextView) mRootView.findViewById(R.id.route_detail));

        // Set the loading message in the fragment TextView.
        mTextView.setText("Loading bus route information...");

        // Request the bus route information to the external service.
        int routeId = Integer.parseInt("22");
        requestBusRouteDetails(routeId);

        return mRootView;
    }

    private void requestBusRouteDetails(int routeId) {

        BusRoutesService.api().findDeparturesByRouteId(
                new FindDeparturesByRouteIdParams(routeId),
                new Callback<FindDeparturesByRouteIdResponse>() {

                    @Override
                    public void success(FindDeparturesByRouteIdResponse departuresResponse, Response response) {

                            /*setListAdapter(new ArrayAdapter<BusRoute>(
                                    getActivity(),
                                    android.R.layout.simple_list_item_activated_1,
                                    android.R.id.text1,
                                    routesResponse.routes)
                            );*/
                        //mAdapter.setRepositories(repositories);

                        renderDepartures(departuresResponse.departures);
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.d("bus", "error finding bus routes by name");
                        // TODO: Toast error
                        //displayErrorMessage();
                    }
                }

        );
    }

    public void renderDepartures(List<Departure> departures) {
        mTextView.setText("Departures for this route:\n");

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
