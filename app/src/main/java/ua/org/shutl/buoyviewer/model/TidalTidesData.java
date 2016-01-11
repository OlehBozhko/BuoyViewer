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
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author shutl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TidalTidesData implements Serializable {

    @JsonProperty("Header")
    private String header;

    @JsonProperty("CurrentDateJson")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD")
    private Date currentDate;
    
    //RawTideDatas[]-- field dropped
    
    @JsonProperty("TideDatas")
    private TideData[] tideDatas;

    @JsonProperty("TideTableDatas")
    private TideTableData[] tideTableDatas;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public TideData[] getTideDatas() {
        return tideDatas;
    }

    public void setTideDatas(TideData[] tideDatas) {
        this.tideDatas = tideDatas;
    }

    public TideTableData[] getTideTableDatas() {
        return tideTableDatas;
    }

    public void setTideTableDatas(TideTableData[] tideTableDatas) {
        this.tideTableDatas = tideTableDatas;
    }

    @Override
    public String toString() {
        return "TidalTidesData{" + "header=" + header
                + ", currentDate=" + currentDate
                + "\n, tideDatas=" + Arrays.toString(tideDatas)
                + "\n, tideTableDatas=" + Arrays.toString(tideTableDatas) + '}';
    }
    
    

}
