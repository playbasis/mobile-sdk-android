package com.playbasis.android.playbasissdk.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TorIsHere on 10/20/2015 AD.
 */
public class EveryNDaysCondition extends Condition {

    @Expose
    @SerializedName("time_of_day")
    private Date timeOfDay;

    @Expose
    @SerializedName("num_of_days")
    private int numOfDays;

    public Date getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Date timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public static EveryNDaysCondition parseEveryNDays(JSONObject conditionJSON) throws JSONException {
        EveryNDaysCondition everyNDaysCondition = new EveryNDaysCondition();

        everyNDaysCondition.setName(conditionJSON.getString("name"));
        everyNDaysCondition.setCategory(conditionJSON.getString("category"));
        everyNDaysCondition.setDescription(conditionJSON.getString("description"));
        everyNDaysCondition.setId(conditionJSON.getString("id"));
        everyNDaysCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        try {
            String dateTime = conditionJSON.getJSONObject("config").getString("time_of_day");
            DateFormat format = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
            Date time = format.parse(dateTime);
            everyNDaysCondition.setTimeOfDay(time);
        } catch (ParseException e) {
            Log.d("Engine Rule Condition", e.getMessage());
        }
        everyNDaysCondition.setNumOfDays(conditionJSON.getJSONObject("config").getInt("num_of_days"));

        return everyNDaysCondition;
    }
}
