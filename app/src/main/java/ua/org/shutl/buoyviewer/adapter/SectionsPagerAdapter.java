package ua.org.shutl.buoyviewer.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ua.org.shutl.buoyviewer.fragment.FragmentFactory;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 09.01.16.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragmentList = new ArrayList<>(10);

    private ViewPager viewPager;

    public SectionsPagerAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(this);
        this.viewPager = viewPager;
        viewPager.setOffscreenPageLimit(1);
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
        int index = fragmentList.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTag();
    }

    public void showLocationItemRootList() {
        fragmentList.clear();
        fragmentList.add(FragmentFactory.newInstance());
        notifyDataSetChanged();
        startPage();
    }

    public void showLocationItemListByParent(long parentId) {
        fragmentList.add(FragmentFactory.newInstance(parentId));
        notifyDataSetChanged();
        nextPage();
    }

    public void showLocationInfoFragment(LocationItem locationItem) {
        fragmentList.add(FragmentFactory.newInstance(locationItem));
        notifyDataSetChanged();
        nextPage();
    }

    public void nextPage() {
        int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1);
    }

    public void previousPage() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem > 0) {
            viewPager.setCurrentItem(currentItem - 1);
        }
    }

    public void startPage() {
        viewPager.setCurrentItem(0);
        setPrimaryItem(null, 0, fragmentList.get(0));
    }

    public PagerAdapter getPagerAdapter() {
        return this;
    }

    @Override
    public void onPageSelected(int position) {
        int fragmentListSize = fragmentList.size();
        int nextPagePosition = position + 1;
        if (fragmentListSize > 1) {
            if (fragmentListSize > nextPagePosition) {
                for (int i = nextPagePosition; i < fragmentListSize; i++) {
                    View view = fragmentList.get(i).getView();
                    fragmentList.remove(i);
                    viewPager.removeView(view);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
}
