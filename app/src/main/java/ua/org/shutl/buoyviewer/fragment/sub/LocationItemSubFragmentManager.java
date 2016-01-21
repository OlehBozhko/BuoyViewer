package ua.org.shutl.buoyviewer.fragment.sub;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 19.01.16.
 */
public class LocationItemSubFragmentManager {

    private FragmentManager fm;

    private LocationItemSubFragmentManager(FragmentManager fm) {
        this.fm = fm;
    }

    private static LocationItemSubFragmentManager instance;

    public static LocationItemSubFragmentManager getInstance(FragmentManager fm) {
        return instance != null ? instance : new LocationItemSubFragmentManager(fm);
    }

    public void attach(LocationItem locationItem) {
        final long locationId = locationItem.getLocationId();
        if (locationItem.isVisibleOnBuoys()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, BuoyInfoFragment.class));
        }
        if (locationItem.isVisibleOnTides()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, TidalGeneralInfoFragment.class));
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, TidalTidesDataFragment.class));
        }
        if (locationItem.isVisibleOnMoonPhases()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, MoonPhasesFragment.class));
        }
        if (locationItem.isVisibleOnWeatherForecast()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnMarineForecast()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnRadar()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnSeaSurfaceTemp()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
        if (locationItem.isVisibleOnWavewatch()) {
            addFragmentToContainer(LocationItemSubFragment.getInstanceById(locationId, EmptyFragment.class));
        }
    }

    private void addFragmentToContainer(Fragment fragment) {
        fm.beginTransaction().add(R.id.content_location_info, fragment).commit();
    }
}
