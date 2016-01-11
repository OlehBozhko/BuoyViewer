/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.rest;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import ua.org.shutl.buoyviewer.model.BuoyInfo;
import ua.org.shutl.buoyviewer.model.LocationItem;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseArray;
import ua.org.shutl.buoyviewer.model.shell.JsonResponseSingle;
import ua.org.shutl.buoyviewer.model.MoonPhases;
import ua.org.shutl.buoyviewer.model.TidalGeneralInfo;
import ua.org.shutl.buoyviewer.model.TidalTidesData;

/**
 *
 * @author shutl
 */
public interface RSService {

    @GET("GetLocationList")
    Call<JsonResponseArray<LocationItem>> getLocationList();

    @GET("GetBouyInfo")
    Call<JsonResponseSingle<BuoyInfo>> getBuoyInfo(@Query("locationId") long locationId);

    @GET("GetTidalGeneralInfo")
    Call<JsonResponseSingle<TidalGeneralInfo>> getTidalGeneralInfo(@Query("locationId") long locationId);

    @GET("GetTidalTidesData")
    Call<JsonResponseSingle<TidalTidesData>> getTidalTidesData(@Query("locationId") long locationId);

    /**
     *
     * @param locationId
     * @param onDate date format DD/MM/yyyy
     * @return
     */
    @GET("GetMoonPhases")
    Call<JsonResponseSingle<MoonPhases>> getMoonPhases(@Query("locationId") long locationId, @Query("onDate") String onDate);
}
