package ua.org.shutl.buoyviewer.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ua.org.shutl.buoyviewer.R;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 07.01.16.
 */
public class LocationItemAdapter extends BaseAdapter {

    private List<LocationItem> locationItems;
    private LayoutInflater layoutInflater;

    public LocationItemAdapter(Context context, List<LocationItem> locationItems) {
        this.locationItems = locationItems;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return locationItems.size();
    }

    @Override
    public Object getItem(int position) {
        return locationItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getLocationItem(position).getLocationId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_location_item, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.textLocationItem);
        textView.setText(getLocationItem(position).getName());
        return view;
    }

    private LocationItem getLocationItem(int position) {
        return (LocationItem) getItem(position);
    }
}
