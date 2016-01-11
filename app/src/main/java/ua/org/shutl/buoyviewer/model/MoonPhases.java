/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author shutl
 */
public class MoonPhases  implements Serializable {

    @JsonProperty("Phases")
    private Phases phases;

    @JsonProperty("SunInfos")
    private SunInfos sunInfos;

    @JsonProperty("MoonInfos")
    private MoonInfo[] moonInfos;

    public Phases getPhases() {
        return phases;
    }

    public void setPhases(Phases phases) {
        this.phases = phases;
    }

    public SunInfos getSunInfos() {
        return sunInfos;
    }

    public void setSunInfos(SunInfos sunInfos) {
        this.sunInfos = sunInfos;
    }

    public MoonInfo[] getMoonInfos() {
        return moonInfos;
    }

    public void setMoonInfos(MoonInfo[] moonInfos) {
        this.moonInfos = moonInfos;
    }

    @Override
    public String toString() {
        return "MoonPhases{" + "phases=" + phases
                + ", sunInfos=" + sunInfos
                + ", moonInfos=" + Arrays.toString(moonInfos) + '}';
    }
}
