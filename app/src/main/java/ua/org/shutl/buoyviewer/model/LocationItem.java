package ua.org.shutl.buoyviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Arrays;

import ua.org.shutl.buoyviewer.BuoysDatabase;

/**
 * @author shutl
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"modelAdapter"})
@Table(name = "location_items", useIsForPrivateBooleans = true, database = BuoysDatabase.class)
public class LocationItem extends BaseModel implements Serializable {

    @JsonProperty("Items")
    private LocationItem[] locationItems;

    @JsonProperty("LocationId")
    @PrimaryKey(autoincrement = false)
    private long locationId;


    @JsonProperty("ParentId")
    @Column
    private long parentId;

    @JsonProperty("ItemType")
    @Column
    private int itemType;

    @JsonProperty("FilterType")
    @Column
    private int filterType;

    @JsonProperty("VisibleOnBuoys")
    @Column
    private boolean visibleOnBuoys;

    @JsonProperty("VisibleOnWeatherForecast")
    @Column
    private boolean visibleOnWeatherForecast;

    @JsonProperty("VisibleOnMarineForecast")
    @Column
    private boolean visibleOnMarineForecast;

    @JsonProperty("VisibleOnTides")
    @Column
    private boolean visibleOnTides;

    @JsonProperty("VisibleOnMoonPhases")
    @Column
    private boolean visibleOnMoonPhases;

    @JsonProperty("VisibleOnRadar")
    @Column
    private boolean visibleOnRadar;

    @JsonProperty("VisibleOnWavewatch")
    @Column
    private boolean visibleOnWavewatch;

    @JsonProperty("VisibleOnSeaSurfaceTemp")
    @Column()
    private boolean visibleOnSeaSurfaceTemp;

    @JsonProperty("Name")
    @Column
    private String name;

    @JsonProperty("Level")
    @Column
    private int level;

    @JsonProperty("InactiveInUI")
    @Column
    private boolean inactiveInUI;

    public LocationItem[] getLocationItems() {
        return locationItems;
    }

    public void setLocationItems(LocationItem[] locationItems) {
        this.locationItems = locationItems;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public boolean isVisibleOnBuoys() {
        return visibleOnBuoys;
    }

    public void setVisibleOnBuoys(boolean visibleOnBuoys) {
        this.visibleOnBuoys = visibleOnBuoys;
    }

    public boolean isVisibleOnWeatherForecast() {
        return visibleOnWeatherForecast;
    }

    public void setVisibleOnWeatherForecast(boolean visibleOnWeatherForecast) {
        this.visibleOnWeatherForecast = visibleOnWeatherForecast;
    }

    public boolean isVisibleOnMarineForecast() {
        return visibleOnMarineForecast;
    }

    public void setVisibleOnMarineForecast(boolean visibleOnMarineForecast) {
        this.visibleOnMarineForecast = visibleOnMarineForecast;
    }

    public boolean isVisibleOnTides() {
        return visibleOnTides;
    }

    public void setVisibleOnTides(boolean visibleOnTides) {
        this.visibleOnTides = visibleOnTides;
    }

    public boolean isVisibleOnMoonPhases() {
        return visibleOnMoonPhases;
    }

    public void setVisibleOnMoonPhases(boolean visibleOnMoonPhases) {
        this.visibleOnMoonPhases = visibleOnMoonPhases;
    }

    public boolean isVisibleOnRadar() {
        return visibleOnRadar;
    }

    public void setVisibleOnRadar(boolean visibleOnRadar) {
        this.visibleOnRadar = visibleOnRadar;
    }

    public boolean isVisibleOnWavewatch() {
        return visibleOnWavewatch;
    }

    public void setVisibleOnWavewatch(boolean visibleOnWavewatch) {
        this.visibleOnWavewatch = visibleOnWavewatch;
    }

    public boolean isVisibleOnSeaSurfaceTemp() {
        return visibleOnSeaSurfaceTemp;
    }

    public void setVisibleOnSeaSurfaceTemp(boolean visibleOnSeaSurfaceTemp) {
        this.visibleOnSeaSurfaceTemp = visibleOnSeaSurfaceTemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isInactiveInUI() {
        return inactiveInUI;
    }

    public void setInactiveInUI(boolean inactiveInUI) {
        this.inactiveInUI = inactiveInUI;
    }

    @Override
    public String toString() {
        return "Item{" + "items=" + Arrays.toString(locationItems) + ", locationId=" + locationId + ", parentId=" + parentId + ", itemType=" + itemType + ", filterType=" + filterType + ", visibleOnBuoys=" + visibleOnBuoys + ", visibleOnWeatherForecast=" + visibleOnWeatherForecast + ", visibleOnMarineForecast=" + visibleOnMarineForecast + ", visibleOnTides=" + visibleOnTides + ", visibleOnMoonPhases=" + visibleOnMoonPhases + ", visibleOnRadar=" + visibleOnRadar + ", visibleOnWavewatch=" + visibleOnWavewatch + ", visibleOnSeaSurfaceTemp=" + visibleOnSeaSurfaceTemp + ", name=" + name + ", level=" + level + ", inactiveInUI=" + inactiveInUI + '}';
    }

}
