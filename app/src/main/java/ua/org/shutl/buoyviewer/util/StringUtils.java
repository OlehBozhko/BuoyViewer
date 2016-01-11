/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.shutl.buoyviewer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shutl
 */
public abstract class StringUtils {

    public static final String EMPTY = "";
    public static final String EET_DATE_TIME = "DD/MM/yyyy HH:mm";
    public static final String EET_DATE = "DD/MM/yyyy";
    public static final String DDMMYYYY_DOT = "dd.MM.yyyy";
    public static final String MMDDYYYY_SLASH = "MM/DD/yyyy";

    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String replaceHtmlDegreeSymbolsToUTF(String string) {
        return string != null ? string.replaceAll("&deg;", "°") : null;
    }

}
