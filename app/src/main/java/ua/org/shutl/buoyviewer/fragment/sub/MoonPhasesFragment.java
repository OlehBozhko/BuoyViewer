package ua.org.shutl.buoyviewer.fragment.sub;

import android.util.Log;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.fragment.sub.LocationItemSubFragment;
import ua.org.shutl.buoyviewer.model.MoonPhases;
import ua.org.shutl.buoyviewer.model.Phases;
import ua.org.shutl.buoyviewer.model.SunInfos;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;
import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * Created by shutl on 10.01.16.
 */
public class MoonPhasesFragment extends LocationItemSubFragment {

    public MoonPhasesFragment() {
        layout = R.layout.fragment_moon_phases;
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

    protected void initData(long id) {
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
