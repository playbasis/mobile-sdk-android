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
public class DailyCondition extends Condition {
    @Expose
    @SerializedName("time_of_day")
    private Date timeOfDay;

    public Date getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Date timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public static DailyCondition parseDailyCondition(JSONObject conditionJSON) throws JSONException {
        DailyCondition dailyCondition = new DailyCondition();

        dailyCondition.setName(conditionJSON.getString("name"));
        dailyCondition.setCategory(conditionJSON.getString("category"));
        dailyCondition.setDescription(conditionJSON.getString("description"));
        dailyCondition.setId(conditionJSON.getString("id"));
        dailyCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        try {
            String dateTime = conditionJSON.getJSONObject("config").getString("time_of_day");
            DateFormat format = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
            Date time = format.parse(dateTime);
            dailyCondition.setTimeOfDay(time);
        } catch (ParseException e) {
            Log.d("Engine Rule Condition", e.getMessage());
        }

        return dailyCondition;
    }
}
