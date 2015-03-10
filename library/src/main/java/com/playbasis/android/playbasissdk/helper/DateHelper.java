package com.playbasis.android.playbasissdk.helper;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 * This object contain static methods for date manipulation.
 */
public class DateHelper {
    public static final String TAG = "DateHelper";

    /**
     * Format a string date into date object. <p>
     * The string should be on the "yyyy-MM-dd'T'HH:mm:ssZ" format
     * @param date string to parse
     * @return date object
     */
    @Nullable public static Date stringToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }
        
    }

    /**
     * Format a date object into date string. 
     * @param date date to format
     * @return date string on the "yyyy-MM-dd'T'HH:mm:ssZ" format
     */
    @Nullable public static String dateToString(Date date){

        SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        try {
            return dateFormat.format(date);
        } catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }
    }

    /**
     * Format a timestamp into http date format. <p>
     * IMPORTANT: the timestamp should be on Unix format and not android format.     
     * @param timeStamp Unix timestamp
     * @return HTTP Date
     */
    @Nullable public static String timestampToHTPPDate(long timeStamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return dateFormat.format(new Date(timeStamp));
        } catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }
    }


    /**
     * Get the current Unix format timestamp.
     * @return timestamp into unix format
     */
    public static long currentTimetamp(){
        return System.currentTimeMillis();
    }
    
}
