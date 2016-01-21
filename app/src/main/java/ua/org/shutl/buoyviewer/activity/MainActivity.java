package ua.org.shutl.buoyviewer.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.dao.LocationItemDao;
import ua.org.shutl.buoyviewer.dao.LocationItemDaoImpl;
import ua.org.shutl.buoyviewer.fragment.MainFragmentManager;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseArray;
import ua.org.shutl.buoyviewer.rest.RSClient;

public class MainActivity extends AppCompatActivity {

    LocationItemDao locationItemDao = new LocationItemDaoImpl();

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.root)
    protected CoordinatorLayout root;

    private MainFragmentManager mMainFragmentManager;
    private volatile boolean updateInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMainFragmentManager = new MainFragmentManager(getSupportFragmentManager());
        mMainFragmentManager.showLocationItemRootList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_refresh:
                updateRootListAndShow();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Toolbar getBar() {
        return toolbar;
    }

    public void updateRootListAndShow() {
        if (updateInProgress) return;

        updateInProgress = true;
        Snackbar.make(root, "Updating data from server...", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();

        Call<JsonResponseArray<LocationItem>> call = RSClient.getApi().getLocationList();
        call.enqueue(new Callback<JsonResponseArray<LocationItem>>() {
            @Override
            public void onResponse(Response<JsonResponseArray<LocationItem>> rspns) {
                if (rspns.body() != null) {
                    JsonResponseArray<LocationItem> locationList = rspns.body();
                    locationItemDao.clearTable();
                    locationItemDao.saveLocationItems(locationList.getResultArray());
                    Snackbar.make(root, "Data received, processing...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    mMainFragmentManager.showLocationItemRootList();
                }
                updateInProgress = false;
            }

            @Override
            public void onFailure(Throwable thrwbl) {
                updateInProgress = false;
                Snackbar.make(root, "Connection to server refused", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
