package ua.org.shutl.buoyviewer.fragment.sub;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.fragment.sub.LocationItemSubFragment;

/**
 * Created by shutl on 10.01.16.
 */
public class EmptyFragment extends LocationItemSubFragment {

    public EmptyFragment() {
        layout = R.layout.fragment_empty;
    }

    @Override
    protected void initData(long id) {
        //Nothing needed to do
    }
}
