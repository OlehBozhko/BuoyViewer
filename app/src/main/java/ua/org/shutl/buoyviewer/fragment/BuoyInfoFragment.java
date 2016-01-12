package ua.org.shutl.buoyviewer.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.BuoyInfo;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;
import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * Created by shutl on 10.01.16.
 */
public class BuoyInfoFragment extends Fragment {

    public static Fragment getInstanceById(long id) {
        Fragment fragment = new BuoyInfoFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buoy_info, container, false);
        ButterKnife.bind(this, rootView);
        long id = getArguments().getLong("id");
        initData(id);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData(long id) {
        Call<JsonResponseSingle<BuoyInfo>> call = RSClient.getApi().getBuoyInfo(id);
        call.enqueue(new Callback<JsonResponseSingle<BuoyInfo>>() {
            @Override
            public void onResponse(Response<JsonResponseSingle<BuoyInfo>> response) {
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