package ua.org.shutl.buoyviewer.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.dao.LocationItemDao;
import ua.org.shutl.buoyviewer.dao.LocationItemDaoImpl;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.model.adapter.LocationItemAdapter;

/**
 * Created by shutl on 20.01.16.
 */
public class LocationItemRootListFragment extends NamedFragment {

    public static String TAG = LocationItemRootListFragment.class.getSimpleName();

    private static LocationItemDao locationItemDao = new LocationItemDaoImpl();
    private static AdapterView.OnItemClickListener onItemClickListener;
    List<LocationItem> locationItems;

    public static Fragment newInstance(AdapterView.OnItemClickListener listener) {
        onItemClickListener = listener;
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
        listView.setOnItemClickListener(onItemClickListener);
        setName(getResources().getString(R.string.app_name));
        return rootView;
    }
}
