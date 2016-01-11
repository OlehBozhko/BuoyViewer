/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author shutl
 */
public class Phases implements Serializable {

    @JsonProperty("NewMoon")
    private String newMoon;

    @JsonProperty("FirstQuarter")
    private String firstQuarter;

    @JsonProperty("FullMoon")
    private String fullMoon;

    @JsonProperty("LastQuarter")
    private String lastQuarter;

    public String getNewMoon() {
        return newMoon;
    }

    public void setNewMoon(String newMoon) {
        this.newMoon = newMoon;
    }

    public String getFirstQuarter() {
        return firstQuarter;
    }

    public void setFirstQuarter(String firstQuarter) {
        this.firstQuarter = firstQuarter;
    }

    public String getFullMoon() {
        return fullMoon;
    }

    public void setFullMoon(String fullMoon) {
        this.fullMoon = fullMoon;
    }

    public String getLastQuarter() {
        return lastQuarter;
    }

    public void setLastQuarter(String lastQuarter) {
        this.lastQuarter = lastQuarter;
    }

    @Override
    public String toString() {
        return "Phases{" + "newMoon=" + newMoon + ", firstQuarter=" + firstQuarter + ", fullMoon=" + fullMoon + ", lastQuarter=" + lastQuarter + '}';
    }

}
