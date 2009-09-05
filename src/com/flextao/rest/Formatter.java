
package com.flextao.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    public static String formateDate(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        return formater.format(date);
    }

    public static Date parseDate(String date) {
        if (date == null) {
            return null;
        }
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        try {
            return formater.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Couldn't parse date string: " + date, e);
        }
    }
}
