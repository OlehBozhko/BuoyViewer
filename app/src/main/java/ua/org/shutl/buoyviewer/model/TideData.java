/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author shutl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TideData implements Serializable {

    @JsonProperty("DayName")
    private String dayName;

    @JsonProperty("Day")
    private int day;

    @JsonProperty("DateTimeJson")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
    private Date dateTime;

    @JsonProperty("Value")
    private float value;

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TideData{" + "dayName=" + dayName
                + ", day=" + day
                + ", dateTime=" + dateTime
                + ", value=" + value + '}';
    }
}
