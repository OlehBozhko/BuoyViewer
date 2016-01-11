/*
 * To change this license header  choose License Headers in Project Properties.
 * To change this template file  choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shutl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TidalGeneralInfo implements Serializable {

    @JsonProperty("LocationId")
    private long locationId;

    @JsonProperty("TideId")
    private String tideId;

    @JsonProperty("TideApiId")
    private String tideApiId;

    @JsonProperty("TideName")
    private String tideName;

    @JsonProperty("TideLatitude")
    private String tideLatitude;// resp. example: "42.589"

    @JsonProperty("TideLongitude")
    private String tideLongitude;// resp. example: "-130.474"

    @JsonProperty("TideCoordinate")
    private String tideCoordinate;// resp. example: "42.589 -130.474"

    @JsonProperty("CurrentDateJson")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD")
    private Date currentDate;

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getTideId() {
        return tideId;
    }

    public void setTideId(String tideId) {
        this.tideId = tideId;
    }

    public String getTideApiId() {
        return tideApiId;
    }

    public void setTideApiId(String tideApiId) {
        this.tideApiId = tideApiId;
    }

    public String getTideName() {
        return tideName;
    }

    public void setTideName(String tideName) {
        this.tideName = tideName;
    }

    public String getTideLatitude() {
        return tideLatitude;
    }

    public void setTideLatitude(String tideLatitude) {
        this.tideLatitude = tideLatitude;
    }

    public String getTideLongitude() {
        return tideLongitude;
    }

    public void setTideLongitude(String tideLongitude) {
        this.tideLongitude = tideLongitude;
    }

    public String getTideCoordinate() {
        return tideCoordinate;
    }

    public void setTideCoordinate(String tideCoordinate) {
        this.tideCoordinate = tideCoordinate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "TidalGeneralInfo{" + "locationId=" + locationId
                + ", tideId=" + tideId
                + ", tideApiId=" + tideApiId
                + ", tideName=" + tideName
                + ", tideLatitude=" + tideLatitude
                + ", tideLongitude=" + tideLongitude
                + ", tideCoordinate=" + tideCoordinate
                + ", currentDate=" + currentDate + '}';
    }
}
