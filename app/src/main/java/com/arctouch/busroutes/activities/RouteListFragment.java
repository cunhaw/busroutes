package com.arctouch.busroutes.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.arctouch.busroutes.R;

import com.arctouch.busroutes.api.BusRoutesService;
import com.arctouch.busroutes.api.FindRoutesByStopNameParams;
import com.arctouch.busroutes.api.FindRoutesByStopNameResponse;
import com.arctouch.busroutes.model.BusRoute;

import java.util.ArrayList;
import java.util.Collections;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A list fragment representing a list of Bus routes. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link RouteDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class RouteListFragment extends ListFragment implements View.OnClickListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The fragment UI object references.
     */
    private Button mSearchButton;
    private EditText mSearchEdit;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * The current Bus Routes result and it's list adapter
     */
    private ArrayList<BusRoute> mBusRoutes;
    private ArrayAdapter mAdapter = null;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String busRouteId);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RouteListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBusRoutes = new ArrayList<BusRoute>();
        mAdapter = new ArrayAdapter<BusRoute>(getActivity(),
           android.R.layout.simple_list_item_activated_1, android.R.id.text1, mBusRoutes);
        setListAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route_list, container, false);

        mSearchButton = ((Button) view.findViewById(R.id.searchButton));
        mSearchEdit = ((EditText) view.findViewById(R.id.searchEdit));

        if (mSearchButton != null)
            mSearchButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    // Implement the OnClickListener callback
    public void onClick(View v) {

        // Clear previous results
        mBusRoutes.clear();
        mAdapter.notifyDataSetChanged();

        // Request bus routes from the service api
        if (mSearchEdit != null)
            requestBusRoutes(String.format("%%%s%%", mSearchEdit.getText()));
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(Integer.toString(mBusRoutes.get(position).id));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    private void requestBusRoutes(String streetNameLike) {
        BusRoutesService.api().findRoutesByStopName(
                new FindRoutesByStopNameParams(streetNameLike),
                new Callback<FindRoutesByStopNameResponse>() {

                    @Override
                    public void success(FindRoutesByStopNameResponse routesResponse, Response response) {

                        Collections.copy(mBusRoutes, routesResponse.routes);
                        mAdapter.notifyDataSetChanged();

                        if (routesResponse.routes.isEmpty()){
                            String noResultsFound = getResources().getString(R.string.no_results_for_street);
                            Toast toast = Toast.makeText(getActivity(), noResultsFound, Toast.LENGTH_SHORT);
                            toast.show();
                        }
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

}
