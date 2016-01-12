package ua.org.shutl.buoyviewer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.TidalTidesData;
import ua.org.shutl.buoyviewer.model.TideData;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.rest.RSClient;

/**
 * Created by shutl on 10.01.16.
 */
public class TidalTidesDataFragment extends Fragment {

    public static Fragment getInstanceById(long id) {
        Fragment fragment = new TidalTidesDataFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.graph)
    GraphView graph;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tidal_tides_data, container, false);
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
        Call<JsonResponseSingle<TidalTidesData>> call = RSClient.getApi().getTidalTidesData(id);
        call.enqueue(new Callback<JsonResponseSingle<TidalTidesData>>() {
            @Override
            public void onResponse(Response<JsonResponseSingle<TidalTidesData>> response) {
                final TidalTidesData data = response.body().getResult();
                final TideData[] tideDatas = data.getTideDatas();
                final DataPoint[] dataPoints = new DataPoint[tideDatas.length];
                for(int i = 0; i<tideDatas.length; i++){
                    dataPoints[i] = new DataPoint(tideDatas[i].getDay(),tideDatas[i].getValue());
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
