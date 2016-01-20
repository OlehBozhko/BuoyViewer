package ua.org.shutl.buoyviewer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.fragment.sub.LocationItemSubFragmentManager;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 19.01.16.
 */
public class LocationItemFragment extends Fragment{

    public static String TAG = LocationItemFragment.class.getSimpleName();

    public static Fragment newInstance(LocationItem locationItem) {
        Fragment fragment = new LocationItemFragment();
        Bundle args = new Bundle();
        args.putSerializable("locationItem", locationItem);
        fragment.setArguments(args);
        return fragment;
    }

    LocationItem locationItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        locationItem = (LocationItem) getArguments().getSerializable("locationItem");
        View rootView = inflater.inflate(R.layout.layout_location_info, container, false);
        LocationItemSubFragmentManager.getInstance(getChildFragmentManager()).attach(locationItem);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LocationItemSubFragmentManager.getInstance(getChildFragmentManager()).detach();
    }

}
