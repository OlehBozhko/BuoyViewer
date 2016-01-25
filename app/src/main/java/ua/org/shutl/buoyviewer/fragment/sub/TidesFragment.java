package ua.org.shutl.buoyviewer.fragment.sub;

import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import butterknife.Bind;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.TidalGeneralInfo;
import ua.org.shutl.buoyviewer.model.TidalTidesData;
import ua.org.shutl.buoyviewer.model.TideData;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;
import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * Created by shutl on 10.01.16.
 */
public class TidesFragment extends SubFragment {

    public TidesFragment() {
        layout = R.layout.fragment_tidal;
    }

    @Bind(R.id.textTideName)
    TextView textTideName;

    @Bind(R.id.textTideLatitude)
    TextView textTideLatitude;

    @Bind(R.id.textTideLongitude)
    TextView textTideLongitude;

    @Bind(R.id.textCurrentDate)
    TextView textCurrentDate;

    @Bind(R.id.graph)
    GraphView graph;

    protected void initData(long id) {
        initTidalGeneralInfo(id);
        initTidalTidesData(id);
    }

    private void initTidalGeneralInfo(long id) {
        Call<JsonResponseSingle<TidalGeneralInfo>> call = RSClient.getApi().getTidalGeneralInfo(id);
        call.enqueue(new Callback<JsonResponseSingle<TidalGeneralInfo>>() {
            @Override
            public void onResponse(Response<JsonResponseSingle<TidalGeneralInfo>> response, Retrofit retrofit) {
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

    private void initTidalTidesData(long id) {
        Call<JsonResponseSingle<TidalTidesData>> call = RSClient.getApi().getTidalTidesData(id);
        call.enqueue(new Callback<JsonResponseSingle<TidalTidesData>>() {
            @Override
            public void onResponse(Response<JsonResponseSingle<TidalTidesData>> response, Retrofit retrofit) {
                final TidalTidesData data = response.body().getResult();
                final TideData[] tideDatas = data.getTideDatas();
                final DataPoint[] dataPoints = new DataPoint[tideDatas.length];
                for (int i = 0; i < tideDatas.length; i++) {
                    dataPoints[i] = new DataPoint(tideDatas[i].getDay(), tideDatas[i].getValue());
                }
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
                graph.addSeries(series);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Error", this.getClass().getName() + " Retrofit callback");

            }
        });
    }
}
