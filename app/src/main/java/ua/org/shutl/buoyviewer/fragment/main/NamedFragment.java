package ua.org.shutl.buoyviewer.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.activity.MainActivity;

/**
 * Created by shutl on 20.01.16.
 */
public abstract class NamedFragment extends Fragment {

    private TextView title;
    private String name;

    protected void setName(String name) {
        if (name == null) {
            this.name = "";
            return;
        }
        if (name.length() > 20) name = name.substring(0, 20);
        this.name = name;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar bar = ((MainActivity) getActivity()).getBar();
        title = (TextView) bar.findViewById(R.id.action_bar_title);
    }

    @Override
    public void onResume() {
        title.setText(name);
        super.onResume();
    }
}
