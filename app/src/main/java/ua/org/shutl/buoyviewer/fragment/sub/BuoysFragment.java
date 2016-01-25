package ua.org.shutl.buoyviewer.fragment.sub;


import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.BuoyInfo;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;

/**
 * Created by shutl on 10.01.16.
 */
public class BuoysFragment extends SubFragment {

    public BuoysFragment() {
        layout = R.layout.fragment_buoys;
    }

    @Bind(R.id.textBuoyName)
    TextView textBuoyName;

    @Bind(R.id.textLatestReading)
    TextView textLatestReading;

    @Bind(R.id.textCoordinate)
    TextView textCoordinate;

    @Bind(R.id.textH2OTemp)
    TextView textH2OTemp;

    @Bind(R.id.textAirTemp)
    TextView textAirTemp;

    @Override
    protected void initData(long id) {
        Call<JsonResponseSingle<BuoyInfo>> call = RSClient.getApi().getBuoyInfo(id);
        call.enqueue(new Callback<JsonResponseSingle<BuoyInfo>>() {

            @Override
            public void onResponse(Response<JsonResponseSingle<BuoyInfo>> response, Retrofit retrofit) {
                final BuoyInfo buoyInfo = response.body().getResult();
                textBuoyName.setText(buoyInfo.getName());
                textLatestReading.setText(buoyInfo.getDateTimeLatestReading());
                textCoordinate.setText(buoyInfo.getCoordinate());
                textH2OTemp.setText(buoyInfo.getH2oTemp());
                textAirTemp.setText(buoyInfo.getAirTemp());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Error", this.getClass().getName() + "Retrofit callback");
            }
        });
    }
}