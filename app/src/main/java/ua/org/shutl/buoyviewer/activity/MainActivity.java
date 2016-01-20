package ua.org.shutl.buoyviewer.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.adapter.MainFragmentManager;
import ua.org.shutl.buoyviewer.dao.LocationItemDao;
import ua.org.shutl.buoyviewer.dao.LocationItemDaoImpl;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseArray;
import ua.org.shutl.buoyviewer.rest.RSClient;

public class MainActivity extends FragmentActivity {

    LocationItemDao locationItemDao = new LocationItemDaoImpl();

    private MainFragmentManager mMainFragmentManager;
    private volatile boolean updateInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mMainFragmentManager = new MainFragmentManager(getSupportFragmentManager(), this);
        mMainFragmentManager.showLocationItemRootList();
    }

    public void onClickBackPage(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void updateRootListAndShow(View view) {
        if(updateInProgress) return;
        updateInProgress = true;
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
                    mMainFragmentManager.showLocationItemRootList();
                }
                updateInProgress = false;
            }

            @Override
            public void onFailure(Throwable thrwbl) {
                updateInProgress = false;
                Snackbar.make(view, "Connection to server refused", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
