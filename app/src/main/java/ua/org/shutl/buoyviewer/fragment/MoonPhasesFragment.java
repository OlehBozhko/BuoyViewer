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
import ua.org.shutl.buoyviewer.model.BuoyInfo;
import ua.org.shutl.buoyviewer.model.MoonPhases;
import ua.org.shutl.buoyviewer.model.Phases;
import ua.org.shutl.buoyviewer.model.SunInfos;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;
import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * Created by shutl on 10.01.16.
 */
public class MoonPhasesFragment extends Fragment {

    public static Fragment getInstanceById(long id) {
        Fragment fragment = new MoonPhasesFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.textNewMoon)
    TextView textNewMoon;

    @Bind(R.id.textFirstQuarter)
    TextView textFirstQuarter;

    @Bind(R.id.textFullMoon)
    TextView textFullMoon;

    @Bind(R.id.textLastQuarter)
    TextView textLastQuarter;

    @Bind(R.id.textSunrise)
    TextView textSunrise;

    @Bind(R.id.textSunset)
    TextView textSunset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_moon_phases, container, false);
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
        final String today = StringUtils.dateToString(new Date(), StringUtils.MMDDYYYY_SLASH);
        Call<JsonResponseSingle<MoonPhases>> call = RSClient.getApi().getMoonPhases(id, today);
        call.enqueue(new Callback<JsonResponseSingle<MoonPhases>>() {
            @Override
            public void onResponse(Response<JsonResponseSingle<MoonPhases>> response) {
                final MoonPhases moonPhases = response.body().getResult();
                final Phases phases = moonPhases.getPhases();
                textNewMoon.setText(phases.getNewMoon());
                textFirstQuarter.setText(phases.getFirstQuarter());
                textFullMoon.setText(phases.getFullMoon());
                textLastQuarter.setText(phases.getLastQuarter());
                final SunInfos sunInfos = moonPhases.getSunInfos();
                final String sunrise = StringUtils.dateToString(sunInfos.getSunrise(), StringUtils.EET_DATE_TIME);
                final String sunset = StringUtils.dateToString(sunInfos.getSunset(), StringUtils.EET_DATE_TIME);
                textSunrise.setText(sunrise);
                textSunset.setText(sunset);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Error", this.getClass().getName() + " Retrofit callback");

            }
        });
    }
}
