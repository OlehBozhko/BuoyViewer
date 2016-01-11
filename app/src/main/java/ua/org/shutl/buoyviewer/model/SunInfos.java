/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author shutl
 */
public class SunInfos  implements Serializable {

    @JsonProperty("Sunrise")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD'T'HH:mm:ss'Z'", timezone = "EET")
    private Date sunrise;

    @JsonProperty("Sunset")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD'T'HH:mm:ss'Z'", timezone = "EET")
    private Date sunset;

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "SunInfos{" + "sunrise=" + sunrise + ", sunset=" + sunset + '}';
    }

}
