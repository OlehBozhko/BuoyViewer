package ua.org.shutl.buoyviewer.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.adapter.SectionsPagerAdapter;
import ua.org.shutl.buoyviewer.dao.LocationItemDao;
import ua.org.shutl.buoyviewer.dao.LocationItemDaoImpl;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseArray;
import ua.org.shutl.buoyviewer.rest.RSClient;

public class MainActivity extends FragmentActivity {

    LocationItemDao locationItemDao = new LocationItemDaoImpl();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mViewPager, this);
        mSectionsPagerAdapter.showLocationItemRootList();
    }

    public void onClickBackPage(View view) {
        mSectionsPagerAdapter.previousPage();
    }

    public void updateRootListAndShow(View view) {
        Snackbar.make(view, "Updating data from server...", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
        Call<JsonResponseArray<LocationItem>> call = RSClient.getApi().getLocationList();
        call.enqueue(new Callback<JsonResponseArray<LocationItem>>() {
            @Override
            public void onResponse(Response<JsonResponseArray<LocationItem>> rspns) {
                if (rspns.body() != null) {
                    JsonResponseArray<LocationItem> locationList = rspns.body();
                    locationItemDao.clearTable();
                    locationItemDao.saveLocationItems(locationList.getResultArray());
                    Snackbar.make(view, "Data received, processing...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    mSectionsPagerAdapter.showLocationItemRootList();
                }
            }

            @Override
            public void onFailure(Throwable thrwbl) {
                Snackbar.make(view, "Connection to server refused", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
