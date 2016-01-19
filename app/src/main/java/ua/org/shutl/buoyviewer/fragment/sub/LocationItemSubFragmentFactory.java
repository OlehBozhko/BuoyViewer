package ua.org.shutl.buoyviewer.fragment.sub;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 19.01.16.
 */
public abstract class LocationItemSubFragmentFactory extends Fragment {

    public static void attach(LocationItem locationItem) {
        final long locationId = locationItem.getLocationId();
        if (locationItem.isVisibleOnBuoys()) {
            addFragment(R.id.content_buoy_info, LocationItemSubFragment.getInstanceById(locationId, BuoyInfoFragment.class));
        }
        if (locationItem.isVisibleOnTides()) {
            addFragment(R.id.content_tidal_general_info, LocationItemSubFragment.getInstanceById(locationId, TidalGeneralInfoFragment.class));
            addFragment(R.id.content_tidal_tides_data, LocationItemSubFragment.getInstanceById(locationId, TidalTidesDataFragment.class));
        }
        if (locationItem.isVisibleOnMoonPhases()) {
            addFragment(R.id.content_moon_phases, LocationItemSubFragment.getInstanceById(locationId, MoonPhasesFragment.class));
        }
        if (locationItem.isVisibleOnWeatherForecast()) {
            addFragment(R.id.content_empty, LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnMarineForecast()) {
            addFragment(R.id.content_empty, LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnRadar()) {
            addFragment(R.id.content_empty, LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnSeaSurfaceTemp()) {
            addFragment(R.id.content_empty, LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnWavewatch()) {
            addFragment(R.id.content_empty, LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
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
}
