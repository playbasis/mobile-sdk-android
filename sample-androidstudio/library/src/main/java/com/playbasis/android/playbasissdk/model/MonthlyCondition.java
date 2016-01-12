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
public class MonthlyCondition extends Condition {
    @Expose
    @SerializedName("time_of_day")
    private Date timeOfDay;

    @Expose
    @SerializedName("day_of_month")
    private int dayOfMonth;

    public Date getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Date timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public static MonthlyCondition parseMonthlyCondition(JSONObject conditionJSON) throws JSONException {
        MonthlyCondition monthlyCondition = new MonthlyCondition();

        monthlyCondition.setName(conditionJSON.getString("name"));
        monthlyCondition.setCategory(conditionJSON.getString("category"));
        monthlyCondition.setDescription(conditionJSON.getString("description"));
        monthlyCondition.setId(conditionJSON.getString("id"));
        monthlyCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        try {
            String dateTime = conditionJSON.getJSONObject("config").getString("time_of_day");
            DateFormat format = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
            Date time = format.parse(dateTime);
            monthlyCondition.setTimeOfDay(time);
        } catch (ParseException e) {
            Log.d("Engine Rule Condition", e.getMessage());
        }
        monthlyCondition.setDayOfMonth(conditionJSON.getJSONObject("config").getInt("day_of_month"));

        return monthlyCondition;
    }

}
