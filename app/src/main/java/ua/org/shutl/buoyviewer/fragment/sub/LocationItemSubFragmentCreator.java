package ua.org.shutl.buoyviewer.fragment.sub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 19.01.16.
 */
public class LocationItemSubFragmentCreator {

    private FragmentManager fm;

    private LocationItemSubFragmentCreator(FragmentManager fm) {
        this.fm = fm;
    }

    private static LocationItemSubFragmentCreator instance;

    public static LocationItemSubFragmentCreator getInstance(FragmentManager fm) {
        return instance != null ? instance : new LocationItemSubFragmentCreator(fm);
    }

    public void attach(LocationItem locationItem) {
        final long locationId = locationItem.getLocationId();
        if (locationItem.isVisibleOnBuoys()) {
            addFragmentToContainer(locationId, BuoyInfoFragment.class);
        }
        if (locationItem.isVisibleOnTides()) {
            addFragmentToContainer(locationId, TidalGeneralInfoFragment.class);
            addFragmentToContainer(locationId, TidalTidesDataFragment.class);
        }
        if (locationItem.isVisibleOnMoonPhases()) {
            addFragmentToContainer(locationId, MoonPhasesFragment.class);
        }
        if (locationItem.isVisibleOnRadar()) {
            addFragmentToContainer(locationId, EmptyFragment.class);
        }
        if (locationItem.isVisibleOnWavewatch()) {
            addFragmentToContainer(locationId, EmptyFragment.class);
        }
    }

    private <T extends LocationItemSubFragment> void addFragmentToContainer(long locationId, Class<T> tClass) {
        Fragment fragment = LocationItemSubFragment.getInstanceById(locationId, tClass);
        fm.beginTransaction().add(R.id.content_location_info, fragment).commit();
    }
}
