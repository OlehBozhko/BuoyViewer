/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import ua.org.shutl.buoyviewer.util.StringUtils;

/**
 * @author shutl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuoyInfo implements Serializable {

    @JsonProperty("LocationId")
    private long locationId;

    @JsonProperty("StationId")
    private String stationId;

    @JsonProperty("BuoyId")
    private String buoyId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("SwellHeight")
    private String swellHeight;

    @JsonProperty("SwellPeriod")
    private String swellPeriod;

    @JsonProperty("SwellDirection")
    private String swellDirection;

    @JsonProperty("SwellDirectionDeg")
    private String swellDirectionDeg;

    @JsonProperty("Coordinate")
    private String coordinate;

    @JsonProperty("DateTimeLatestReading")
    private String dateTimeLatestReading;

    @JsonProperty("WindSpeed")
    private String windSpeed;

    @JsonProperty("WindDirection")
    private String windDirection;

    @JsonProperty("MeanWaveDirection")
    private String meanWaveDirection;

    @JsonProperty("WindDirectionPercent")
    private String windDirectionPercent;

    @JsonProperty("WindGust")
    private String windGust;

    @JsonProperty("WaveHeight")
    private String waveHeight;

    @JsonProperty("WavePeriod")
    private String wavePeriod;

    @JsonProperty("H2OTemp")//&deg here
    private String h2oTemp;

    @JsonProperty("AirTemp")//&deg here
    private String airTemp;

    @JsonProperty("WeatherForecast")
    private String weatherForecast;

    @JsonProperty("SwellCondition")
    private String swellCondition;

    @JsonProperty("MarineForecastToday")
    private String marineForecastToday;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getBuoyId() {
        return buoyId;
    }

    public void setBuoyId(String buoyId) {
        this.buoyId = buoyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwellHeight() {
        return swellHeight;
    }

    public void setSwellHeight(String swellHeight) {
        this.swellHeight = swellHeight;
    }

    public String getSwellPeriod() {
        return swellPeriod;
    }

    public void setSwellPeriod(String swellPeriod) {
        this.swellPeriod = swellPeriod;
    }

    public String getSwellDirection() {
        return swellDirection;
    }

    public void setSwellDirection(String swellDirection) {
        this.swellDirection = swellDirection;
    }

    public String getSwellDirectionDeg() {
        return swellDirectionDeg;
    }

    public void setSwellDirectionDeg(String swellDirectionDeg) {
        this.swellDirectionDeg = swellDirectionDeg;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getDateTimeLatestReading() {
        return dateTimeLatestReading;
    }

    public void setDateTimeLatestReading(String dateTimeLatestReading) {
        this.dateTimeLatestReading = dateTimeLatestReading;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getMeanWaveDirection() {
        return meanWaveDirection;
    }

    public void setMeanWaveDirection(String meanWaveDirection) {
        this.meanWaveDirection = StringUtils.replaceHtmlDegreeSymbolsToUTF(meanWaveDirection);
    }

    public String getWindDirectionPercent() {
        return windDirectionPercent;
    }

    public void setWindDirectionPercent(String windDirectionPercent) {
        this.windDirectionPercent = windDirectionPercent;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    public String getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(String waveHeight) {
        this.waveHeight = waveHeight;
    }

    public String getWavePeriod() {
        return wavePeriod;
    }

    public void setWavePeriod(String wavePeriod) {
        this.wavePeriod = wavePeriod;
    }

    public String getH2oTemp() {
        return h2oTemp;
    }

    public void setH2oTemp(String h2oTemp) {
        System.out.println("invoced");
        this.h2oTemp = StringUtils.replaceHtmlDegreeSymbolsToUTF(h2oTemp);
    }

    public String getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(String airTemp) {
        this.airTemp = StringUtils.replaceHtmlDegreeSymbolsToUTF(airTemp);
    }

    public String getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(String weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public String getSwellCondition() {
        return swellCondition;
    }

    public void setSwellCondition(String swellCondition) {
        this.swellCondition = swellCondition;
    }

    public String getMarineForecastToday() {
        return marineForecastToday;
    }

    public void setMarineForecastToday(String marineForecastToday) {
        this.marineForecastToday = marineForecastToday;
    }

    @Override
    public String toString() {
        return "BuoyInfo{" + "locationId=" + locationId + ", stationId=" + stationId + ", buoyId=" + buoyId + ", name=" + name + ", swellHeight=" + swellHeight + ", swellPeriod=" + swellPeriod + ", swellDirection=" + swellDirection + ", swellDirectionDeg=" + swellDirectionDeg + ", coordinate=" + coordinate + ", dateTimeLatestReading=" + dateTimeLatestReading + ", windSpeed=" + windSpeed + ", windDirection=" + windDirection + ", meanWaveDirection=" + meanWaveDirection + ", windDirectionPercent=" + windDirectionPercent + ", windGust=" + windGust + ", waveHeight=" + waveHeight + ", wavePeriod=" + wavePeriod + ", H2OTemp=" + h2oTemp + ", airTemp=" + airTemp + ", weatherForecast=" + weatherForecast + ", swellCondition=" + swellCondition + ", marineForecastToday=" + marineForecastToday + '}';
    }
}
