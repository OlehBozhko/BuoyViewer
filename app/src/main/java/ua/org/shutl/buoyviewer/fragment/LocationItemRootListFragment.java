package ua.org.shutl.buoyviewer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.adapter.MainFragmentManager;
import ua.org.shutl.buoyviewer.dao.LocationItemDao;
import ua.org.shutl.buoyviewer.dao.LocationItemDaoImpl;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.model.adapter.LocationItemAdapter;

/**
 * Created by shutl on 20.01.16.
 */
public class LocationItemRootListFragment extends Fragment{

    public static String TAG = LocationItemRootListFragment.class.getSimpleName();

    private static LocationItemDao locationItemDao = new LocationItemDaoImpl();
    private static MainFragmentManager pagerAdapter;
    List<LocationItem> locationItems;

    public static Fragment newInstance(MainFragmentManager adapter) {
        pagerAdapter = adapter;
        Fragment fragment = new LocationItemRootListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        locationItems = locationItemDao.getLocationItemsRootList();
        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(new LocationItemAdapter(getActivity(), locationItems));
        listView.setOnItemClickListener(pagerAdapter);
        return rootView;
    }


}
