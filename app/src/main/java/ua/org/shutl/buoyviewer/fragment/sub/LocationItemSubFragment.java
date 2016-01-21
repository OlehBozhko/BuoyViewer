package ua.org.shutl.buoyviewer.fragment.sub;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by shutl on 19.01.16.
 */
public abstract class LocationItemSubFragment extends Fragment{

    @LayoutRes
    protected int layout;

    public static <T extends LocationItemSubFragment> Fragment getInstanceById(long id, Class<T> tClass) {
        Fragment fragment = null;
        try {
            fragment = tClass.newInstance();
            Bundle args = new Bundle();
            args.putLong("id", id);
            fragment.setArguments(args);
        } catch (Exception e) {
            Log.w("LocationItemSubFragment","Can't find this object");
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(layout, container, false);
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

    public void setLayout(int layout) {
        this.layout = layout;
    }

    protected  abstract void initData(long id);
}
