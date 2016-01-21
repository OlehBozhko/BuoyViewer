package ua.org.shutl.buoyviewer.fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ua.org.shutl.buoyviewer.fragment.sub.BuoyInfoFragment;
import ua.org.shutl.buoyviewer.fragment.sub.EmptyFragment;
import ua.org.shutl.buoyviewer.fragment.sub.LocationItemSubFragment;
import ua.org.shutl.buoyviewer.fragment.sub.MoonPhasesFragment;
import ua.org.shutl.buoyviewer.fragment.sub.TidalGeneralInfoFragment;
import ua.org.shutl.buoyviewer.fragment.sub.TidalTidesDataFragment;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 19.01.16.
 */
public abstract class LocationItemSubFragmentFactory {

    public static List<Fragment> create(LocationItem locationItem) {
        final long locationId = locationItem.getLocationId();
        final List<Fragment> fragments = new ArrayList<>();

        if (locationItem.isVisibleOnBuoys()) {
            fragments.add(buildFragment(locationId, BuoyInfoFragment.class)) ;
        }
        if (locationItem.isVisibleOnTides()) {
            fragments.add(buildFragment(locationId, TidalGeneralInfoFragment.class));
            fragments.add(buildFragment(locationId, TidalTidesDataFragment.class));
        }
        if (locationItem.isVisibleOnMoonPhases()) {
            fragments.add(buildFragment(locationId, MoonPhasesFragment.class));
        }
        if (locationItem.isVisibleOnRadar()) {
            fragments.add(buildFragment(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnWavewatch()) {
            fragments.add(buildFragment(locationId, EmptyFragment.class));
        }
        return fragments;
    }

    private static <T extends LocationItemSubFragment> Fragment buildFragment(long locationId, Class<T> tClass) {
        return LocationItemSubFragment.getInstanceById(locationId, tClass);
    }
}
