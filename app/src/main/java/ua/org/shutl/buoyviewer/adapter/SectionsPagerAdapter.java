package ua.org.shutl.buoyviewer.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.fragment.FragmentFactory;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 09.01.16.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter
        implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {

    private final Activity activity;
    private List<Fragment> fragmentList = new ArrayList<>(10);
    private List<String> pageNames = new ArrayList<String>(10) {
        {
            add("BuoyViewer");
        }
    };
    TextView textView;

    private ViewPager viewPager;

    public SectionsPagerAdapter(FragmentManager fm, ViewPager viewPager, Activity activity) {
        super(fm);
        this.activity = activity;
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(this);
        this.viewPager = viewPager;
        viewPager.setOffscreenPageLimit(1);
        FragmentFactory.setPagerAdapter(this);
        textView = (TextView) activity.findViewById(R.id.action_bar_title);
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
            int previousPage = currentItem - 1;
            viewPager.setCurrentItem(previousPage);
            textView.setText(pageNames.get(previousPage));
            pageNames.remove(currentItem);
        }
    }

    public void startPage() {
        viewPager.setCurrentItem(0);
        setPrimaryItem(null, 0, fragmentList.get(0));
    }

    public void setNextToolbarHeader(String text) {
        if (text.length() > 20) text = text.substring(0, 20);
        pageNames.add(text);
        textView.setText(text);
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
                    clearFragmentView(view);
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

    private void clearFragmentView(View view) {
        View buoyInfo = view.findViewById(R.id.content_buoy_info);
        viewPager.removeView(buoyInfo);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LocationItem locationItem = (LocationItem) parent.getItemAtPosition(position);
        setNextToolbarHeader(locationItem.getName());
//        Log.w("LocationItem: ", locationItem.toString());// DIAG. to remove
        final int itemType = locationItem.getItemType();
        if (itemType == 0 || itemType == 1) {
            showLocationItemListByParent(locationItem.getLocationId());
        } else if (itemType == 2) {
            showLocationInfoFragment(locationItem);
        }
    }
}
