package ua.org.shutl.buoyviewer.fragment.sub;

import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.TidalGeneralInfo;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;
import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * Created by shutl on 10.01.16.
 */
public class TidalGeneralInfoFragment extends LocationItemSubFragment {

    public TidalGeneralInfoFragment() {
        layout = R.layout.fragment_tidal_general_info;
    }

    @Bind(R.id.textTideName)
    TextView textTideName;

    @Bind(R.id.textTideLatitude)
    TextView textTideLatitude;

    @Bind(R.id.textTideLongitude)
    TextView textTideLongitude;

    @Bind(R.id.textCurrentDate)
    TextView textCurrentDate;

    protected void initData(long id) {
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
