/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.rest;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import ua.org.shutl.buoyviewer.util.Constants;

/**
 *
 * @author shutl
 */
public class RSClient {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private static final RSService service = retrofit.create(RSService.class);

    public static RSService getApi() {
        return service;
    }
}
