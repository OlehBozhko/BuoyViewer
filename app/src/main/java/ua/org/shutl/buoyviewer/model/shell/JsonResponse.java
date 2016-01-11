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
 */
public abstract class JsonResponse {

    @JsonProperty("ResultCode")
    protected String resultCode;

    @JsonProperty("ResultCodeName")
    protected String resultCodeName;

    @JsonProperty("ErrorMessage")
    protected String errorMessage;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCodeName() {
        return resultCodeName;
    }

    public void setResultCodeName(String resultCodeName) {
        this.resultCodeName = resultCodeName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" + "resultCode=" + resultCode + 
                ", resultCodeName=" + resultCodeName + 
                ", errorMessage=" + errorMessage + '}';
    }
}
