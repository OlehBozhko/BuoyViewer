package ua.org.shutl.buoyviewer.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
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
 * Created by shutl on 08.01.16.
 */
public abstract class FragmentFactory {

    private static LocationItemDao locationItemDao = new LocationItemDaoImpl();

    private static SectionsPagerAdapter pagerAdapter;

    public static void setPagerAdapter(SectionsPagerAdapter pagerAdapter) {
        FragmentFactory.pagerAdapter = pagerAdapter;
    }

    public static Fragment newInstance() {
        Fragment fragment = new LocationItemRootListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance(long parentId) {
        Fragment fragment = new LocationItemListFragment();
        Bundle args = new Bundle();
        args.putLong("parentId", parentId);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance(LocationItem locationItem) {
        Fragment fragment = new LocationItemFragment();
        Bundle args = new Bundle();
        args.putSerializable("locationItem", locationItem);
        fragment.setArguments(args);
        return fragment;
    }

    public static class LocationItemFragment extends Fragment {

        LocationItem locationItem;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            locationItem = (LocationItem) getArguments().getSerializable("locationItem");
            View rootView = inflater.inflate(R.layout.layout_location_info, container, false);
            initFragments();
            return rootView;
        }

        private void initFragments() {
            final long locationId = locationItem.getLocationId();
            if (locationItem.isVisibleOnBuoys()) {
                addFragment(R.id.content_buoy_info, BuoyInfoFragment.getInstanceById(locationId));
            }
            if (locationItem.isVisibleOnTides()) {
                addFragment(R.id.content_tidal_general_info, TidalGeneralInfoFragment.getInstanceById(locationId));
                addFragment(R.id.content_tidal_tides_data, TidalTidesDataFragment.getInstanceById(locationId));
            }
            if (locationItem.isVisibleOnMoonPhases()) {
                addFragment(R.id.content_moon_phases, MoonPhasesFragment.getInstanceById(locationId));
            }
            if (locationItem.isVisibleOnWeatherForecast()) {
                addFragment(R.id.content_empty, EmptyFragment.getInstanceById(locationId));
            }
            if (locationItem.isVisibleOnMarineForecast()) {
                addFragment(R.id.content_empty, EmptyFragment.getInstanceById(locationId));
            }
            if (locationItem.isVisibleOnRadar()) {
                addFragment(R.id.content_empty, EmptyFragment.getInstanceById(locationId));
            }
            if (locationItem.isVisibleOnSeaSurfaceTemp()) {
                addFragment(R.id.content_empty, EmptyFragment.getInstanceById(locationId));
            }
            if (locationItem.isVisibleOnWavewatch()) {
                addFragment(R.id.content_empty, EmptyFragment.getInstanceById(locationId));
            }
        }

        private void addFragment(@IdRes int containerViewId, Fragment fragment) {
            getChildFragmentManager().beginTransaction().add(containerViewId, fragment).commit();
        }

        private void removeFragment(@IdRes int containerViewId) {
            Fragment fragment = getChildFragmentManager().findFragmentById(containerViewId);
            if (fragment != null && fragment.isAdded() && !fragment.isRemoving() && !fragment.isDetached()) {
                getChildFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            removeFragment(R.id.content_buoy_info);
            removeFragment(R.id.content_tidal_general_info);
            removeFragment(R.id.content_tidal_tides_data);
            removeFragment(R.id.content_moon_phases);
            removeFragment(R.id.content_empty);
        }
    }

    public static class LocationItemRootListFragment extends Fragment {

        List<LocationItem> locationItems;

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

    public static class LocationItemListFragment extends Fragment {

        List<LocationItem> locationItems;

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
}