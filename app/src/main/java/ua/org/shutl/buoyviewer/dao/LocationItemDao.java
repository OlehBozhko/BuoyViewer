package ua.org.shutl.buoyviewer.dao;

import java.util.List;

import ua.org.shutl.buoyviewer.model.LocationItem;

/**
 * Created by shutl on 08.01.16.
 */
public abstract class LocationItemDao extends GenericDao<LocationItem>{

    public LocationItemDao() {
        super(LocationItem.class);
    }

    public abstract List<LocationItem> getLocationItemsRootList();

    public abstract List<LocationItem> getLocationItemsListByParentId(long parentId);

    public abstract void saveLocationItems(LocationItem[] items);

}
