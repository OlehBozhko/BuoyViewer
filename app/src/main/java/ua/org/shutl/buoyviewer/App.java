package ua.org.shutl.buoyviewer;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by shutl on 07.01.16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
