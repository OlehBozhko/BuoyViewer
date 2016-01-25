package ua.org.shutl.buoyviewer.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ua.org.shutl.buoyviewer.fragment.sub.SubFragment;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * Created by shutl on 19.01.16.
 */
public abstract class SubFragmentFactory {

    private final static String packagee = "ua.org.shutl.buoyviewer.fragment.sub.";
    private final static String keyWord = "VisibleOn";
    private final static String fragmentPostfix = "Fragment";

    public static List<Fragment> create(LocationItem locationItem) {
        final long locationId = locationItem.getLocationId();
        final List<Fragment> fragments = new ArrayList<>();
        List<String> availableTypesList = getAvailableTypesList(locationItem);
        for (String type : availableTypesList) {
            try {
                Class<? extends SubFragment> clazz =
                        (Class<? extends SubFragment>) Class.forName(packagee + type);
                fragments.add(buildFragment(locationId, clazz));
            } catch (ClassNotFoundException | ClassCastException e) {
                Log.w("FTAG",e.toString());
            }
        }
        return fragments;
    }


    private static List<String> getAvailableTypesList(LocationItem locationItem) {
        List<String> typesList = new LinkedList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(locationItem);
        Iterator<String> fields = node.fieldNames();
        while (fields.hasNext()) {
            String field = fields.next();
            if (field.startsWith(keyWord) && node.path(field).asBoolean()) {
                String className = field.replaceFirst(keyWord, StringUtils.EMPTY).concat(fragmentPostfix);
                typesList.add(className);
            }
        }
        return typesList;
    }

    private static <T extends SubFragment> Fragment buildFragment(long locationId, Class<T> tClass) {
        return SubFragment.getInstanceById(locationId, tClass);
    }
}
