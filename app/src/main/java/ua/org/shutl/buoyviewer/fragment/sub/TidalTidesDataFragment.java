package ua.org.shutl.buoyviewer.fragment.sub;

import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import butterknife.Bind;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.fragment.sub.LocationItemSubFragment;
import ua.org.shutl.buoyviewer.model.TidalTidesData;
import ua.org.shutl.buoyviewer.model.TideData;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;

/**
 * Created by shutl on 10.01.16.
 */
public class TidalTidesDataFragment extends LocationItemSubFragment {

    public TidalTidesDataFragment() {
        layout = R.layout.fragment_tidal_tides_data;
    }

    @Bind(R.id.graph)
    GraphView graph;

    protected void initData(long id) {
        Call<JsonResponseSingle<TidalTidesData>> call = RSClient.getApi().getTidalTidesData(id);
        call.enqueue(new Callback<JsonResponseSingle<TidalTidesData>>() {
            @Override
            public void onResponse(Response<JsonResponseSingle<TidalTidesData>> response) {
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
