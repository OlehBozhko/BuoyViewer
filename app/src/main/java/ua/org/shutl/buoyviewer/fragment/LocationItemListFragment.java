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
import ua.org.shutl.buoyviewer.adapter.SectionsPagerAdapter;
import ua.org.shutl.buoyviewer.dao.LocationItemDao;
import ua.org.shutl.buoyviewer.dao.LocationItemDaoImpl;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.model.adapter.LocationItemAdapter;

/**
 * Created by shutl on 20.01.16.
 */
public class LocationItemListFragment extends Fragment {

    private static LocationItemDao locationItemDao = new LocationItemDaoImpl();
    private static SectionsPagerAdapter pagerAdapter;
    List<LocationItem> locationItems;

    public static Fragment newInstance(long parentId, SectionsPagerAdapter adapter) {
        pagerAdapter = adapter;
        Fragment fragment = new LocationItemListFragment();
        Bundle args = new Bundle();
        args.putLong("parentId", parentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        long parentId = getArguments().getLong("parentId");
        locationItems = locationItemDao.getLocationItemsListByParentId(parentId);
        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(new LocationItemAdapter(getActivity(), locationItems));
        listView.setOnItemClickListener(pagerAdapter);
        return rootView;
    }
}
