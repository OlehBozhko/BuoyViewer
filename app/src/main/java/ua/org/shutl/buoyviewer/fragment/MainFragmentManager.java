package ua.org.shutl.buoyviewer.fragment;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.fragment.main.LocationItemFragment;
import ua.org.shutl.buoyviewer.fragment.main.LocationItemListFragment;
import ua.org.shutl.buoyviewer.fragment.main.LocationItemRootListFragment;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 09.01.16.
 */
public class MainFragmentManager implements AdapterView.OnItemClickListener {

    @IdRes
    private int containerId = R.id.container;
    private final FragmentManager fragmentManager;

    public MainFragmentManager(FragmentManager fm) {
        fragmentManager = fm;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LocationItem locationItem = (LocationItem) parent.getItemAtPosition(position);
        final int itemType = locationItem.getItemType();
        if (itemType == 0 || itemType == 1) {
            showLocationItemListByParent(locationItem.getLocationId(), locationItem.getName());
        } else if (itemType == 2) {
            showLocationInfoFragment(locationItem);
        }
    }

    public void showLocationItemRootList() {
        startPage();
        addOrReplaceFragment(R.id.container, LocationItemRootListFragment.newInstance(this));
    }

    private void showLocationItemListByParent(long parentId, String name) {
        addFragmentToBackStack(containerId, LocationItemListFragment.newInstance(parentId, name, this));
    }

    private void showLocationInfoFragment(LocationItem locationItem) {
        addFragmentToBackStack(containerId, LocationItemFragment.newInstance(locationItem));
    }

    private void startPage() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void addOrReplaceFragment(@IdRes int containerViewId, Fragment fragment) {
        Fragment oldFragment = fragmentManager.findFragmentById(containerViewId);
        if (oldFragment != null && oldFragment.isAdded()) {
            fragmentManager.beginTransaction().replace(containerViewId, fragment).commit();
        } else {
            fragmentManager.beginTransaction().add(containerViewId, fragment).commit();
        }
    }

    private void addFragmentToBackStack(@IdRes int containerViewId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(null)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
