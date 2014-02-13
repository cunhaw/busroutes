package com.arctouch.busroutes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arctouch.busroutes.api.BusRoutesService;
import com.arctouch.busroutes.api.FindDeparturesByRouteIdParams;
import com.arctouch.busroutes.api.FindDeparturesByRouteIdResponse;
import com.arctouch.busroutes.api.FindRoutesByStopNameParams;
import com.arctouch.busroutes.api.FindRoutesByStopNameResponse;
import com.arctouch.busroutes.dummy.DummyContent;
import com.arctouch.busroutes.model.BusRoute;
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
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * The dummy content this fragment is presenting.
     */
    private BusRoute mBusRoute;
    private List<Departure> mDepartures;

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
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            int routeId = Integer.parseInt("22");
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

                            // TODO: just update the adapter instead of creating a new one?
                            //mAdapter.setRepositories(repositories);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_route_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.route_detail)).setText(mItem.content);
        }

        return rootView;
    }
}
