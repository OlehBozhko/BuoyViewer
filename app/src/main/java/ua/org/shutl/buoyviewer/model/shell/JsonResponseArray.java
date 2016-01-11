/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.model.shell;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author shutl
 * @param <T>
 */
public class JsonResponseArray<T> extends JsonResponse {

    @JsonProperty("ReturnValue")
    private T[] resultArray;

    public T[] getResultArray() {
        return resultArray;
    }

    public void setResultArray(T[] resultArray) {
        this.resultArray = resultArray;
    }
}
