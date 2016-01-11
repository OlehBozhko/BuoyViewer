package ua.org.shutl.buoyviewer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import ua.org.shutl.buoyviewer.fragment.FragmentFactory;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 09.01.16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>(10);
    private ViewPager viewPager;

    public SectionsPagerAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        viewPager.setAdapter(this);
        viewPager.setOffscreenPageLimit(0);
        this.viewPager = viewPager;
        initViewPagerListeners();
        FragmentFactory.setPagerAdapter(this);
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    public void loadRootList() {
        fragmentList.clear();
        fragmentList.add(FragmentFactory.newInstance());
        notifyDataSetChanged();
        viewPager.setCurrentItem(0, true);
    }


    public void showLocationItemListByParent(long parentId) {
        fragmentList.add(FragmentFactory.newInstance(parentId));
        notifyDataSetChanged();
        int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1, true);
    }

    public void showLocationInfoFragment(LocationItem locationItem) {
        fragmentList.add(FragmentFactory.newInstance(locationItem));
        notifyDataSetChanged();
        int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1, true);
    }

    public void initViewPagerListeners() {

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                int fragmentListSize = fragmentList.size();
                int incrementedPosition = position + 1;
                if (fragmentListSize > 1) {
                    if (fragmentListSize > incrementedPosition) {
                        for (int i = incrementedPosition; i < fragmentListSize; i++) {
                            fragmentList.remove(i);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });
    }
}
