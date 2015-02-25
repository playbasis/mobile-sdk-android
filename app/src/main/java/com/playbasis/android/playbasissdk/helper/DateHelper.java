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
 */
public class DateHelper {
    public static final String TAG = "DateHelper";
    
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
    
    @Nullable public static String dateToString(Date date){

        SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        try {
            return dateFormat.format(date);
        } catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }
    }
    
    @Nullable public static String timestampToHTPPDate(long timeStamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.getDefault());
   //     dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        try {
            return dateFormat.format(new Date(timeStamp));
        } catch (NullPointerException ne){
            ne.printStackTrace();
            return null;
        }
    }
    
    
    public static long currentTimetamp(){
        return System.currentTimeMillis()/1000;
    }
    
}
