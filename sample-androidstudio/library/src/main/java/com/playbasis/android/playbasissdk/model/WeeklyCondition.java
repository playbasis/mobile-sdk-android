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
public class WeeklyCondition extends Condition {

    @Expose
    @SerializedName("time_of_day")
    private Date timeOfDay;

    @Expose
    @SerializedName("day_of_week")
    private String dayOfWeek;

    public Date getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Date timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public static WeeklyCondition parseWeeklyCondition(JSONObject conditionJSON) throws JSONException {
        WeeklyCondition weeklyCondition = new WeeklyCondition();

        weeklyCondition.setName(conditionJSON.getString("name"));
        weeklyCondition.setCategory(conditionJSON.getString("category"));
        weeklyCondition.setDescription(conditionJSON.getString("description"));
        weeklyCondition.setId(conditionJSON.getString("id"));
        weeklyCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        try {
            String dateTime = conditionJSON.getJSONObject("config").getString("time_of_day");
            DateFormat format = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
            Date time = format.parse(dateTime);
            weeklyCondition.setTimeOfDay(time);
        } catch (ParseException e) {
            Log.d("Engine Rule Condition", e.getMessage());
        }
        weeklyCondition.setDayOfWeek(conditionJSON.getJSONObject("config").getString("day_of_month"));

        return weeklyCondition;
    }
}
