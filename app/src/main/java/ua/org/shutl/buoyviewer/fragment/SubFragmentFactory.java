package ua.org.shutl.buoyviewer.fragment;

import android.support.v4.app.Fragment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ua.org.shutl.buoyviewer.fragment.sub.SubFragment;
import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 19.01.16.
 */
public abstract class SubFragmentFactory {

    private final static String PACKAGE = "ua.org.shutl.buoyviewer.fragment.sub.";
    private final static String KEY_WORD = "VisibleOn";
    private final static String FRAGMENT_POSTFIX = "Fragment";

    public static List<Fragment> create(LocationItem locationItem) {
        final long locationId = locationItem.getLocationId();
        final List<Fragment> fragments = new ArrayList<>();
        for (String type : getAvailableTypesList(locationItem)) {
            try {
                Class<? extends SubFragment> clazz = (Class<? extends SubFragment>) Class.forName(type);
                fragments.add(buildFragment(locationId, clazz));
            } catch (Exception e) {
            }
        }
        return fragments;
    }


    private static <T extends SubFragment> Fragment buildFragment(long locationId, Class<T> tClass) {
        return SubFragment.getInstanceById(locationId, tClass);
    }

    private static List<String> getAvailableTypesList(LocationItem locationItem) {
        List<String> typesList = new LinkedList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(locationItem);
        Iterator<String> fields = node.fieldNames();
        while (fields.hasNext()) {
            String field = fields.next();
            if (field.startsWith(KEY_WORD) && node.path(field).asBoolean()) {
                String className = field.replaceFirst(KEY_WORD, PACKAGE).concat(FRAGMENT_POSTFIX);
                typesList.add(className);
            }
        }
        return typesList;
    }
}
