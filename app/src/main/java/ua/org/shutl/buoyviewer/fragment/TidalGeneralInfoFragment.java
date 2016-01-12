package ua.org.shutl.buoyviewer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import ua.org.shutl.buoyviewer.model.MoonPhases;
import ua.org.shutl.buoyviewer.model.Phases;
import ua.org.shutl.buoyviewer.model.SunInfos;
import ua.org.shutl.buoyviewer.model.TidalGeneralInfo;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;
import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * Created by shutl on 10.01.16.
 */
public class TidalGeneralInfoFragment extends Fragment {

    public static Fragment getInstanceById(long id) {
        Fragment fragment = new TidalGeneralInfoFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.textTideName)
    TextView textTideName;

    @Bind(R.id.textTideLatitude)
    TextView textTideLatitude;

    @Bind(R.id.textTideLongitude)
    TextView textTideLongitude;

    @Bind(R.id.textCurrentDate)
    TextView textCurrentDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tidal_general_info, container, false);
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
        Call<JsonResponseSingle<TidalGeneralInfo>> call = RSClient.getApi().getTidalGeneralInfo(id);
        call.enqueue(new Callback<JsonResponseSingle<TidalGeneralInfo>>() {
            @Override
            public void onResponse(Response<JsonResponseSingle<TidalGeneralInfo>> response) {
                final TidalGeneralInfo info = response.body().getResult();
                textTideName.setText(info.getTideName());
                textTideLatitude.setText(info.getTideLatitude());
                textTideLongitude.setText(info.getTideLongitude());
                String currentDate = StringUtils.dateToString(info.getCurrentDate(), StringUtils.EET_DATE);
                textCurrentDate.setText(currentDate);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Error", this.getClass().getName() + " Retrofit callback");

            }
        });
    }
}
