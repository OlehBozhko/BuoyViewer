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
public class TideTableData implements Serializable {

    @JsonProperty("DayName")
    private String dayName;

    @JsonProperty("DateTimeJson")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD'T'HH:mm:ss")
    private Date dateTime;

    @JsonProperty("Day")
    private int day;

    @JsonProperty("LowFirst")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
    private Date lowFirst;

    @JsonProperty("HighFirst")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
    private Date highFirst;

    @JsonProperty("LowSecond")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
    private Date lowSecond;

    @JsonProperty("HighSecond")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
    private Date highSecond;

    @JsonProperty("LowThird")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
    private Date lowThird;

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getLowFirst() {
        return lowFirst;
    }

    public void setLowFirst(Date lowFirst) {
        this.lowFirst = lowFirst;
    }

    public Date getHighFirst() {
        return highFirst;
    }

    public void setHighFirst(Date highFirst) {
        this.highFirst = highFirst;
    }

    public Date getLowSecond() {
        return lowSecond;
    }

    public void setLowSecond(Date lowSecond) {
        this.lowSecond = lowSecond;
    }

    public Date getHighSecond() {
        return highSecond;
    }

    public void setHighSecond(Date highSecond) {
        this.highSecond = highSecond;
    }

    public Date getLowThird() {
        return lowThird;
    }

    public void setLowThird(Date lowThird) {
        this.lowThird = lowThird;
    }

    @Override
    public String toString() {
        return "TideTableData{" + "dayName=" + dayName
                + ", dateTime=" + dateTime
                + ", day=" + day
                + ", lowFirst=" + lowFirst
                + ", highFirst=" + highFirst
                + ", lowSecond=" + lowSecond
                + ", highSecond=" + highSecond
                + ", lowThird=" + lowThird + '}';
    }

}
