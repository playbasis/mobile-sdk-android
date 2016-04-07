package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TorIsHere on 3/11/2016 AD.
 */
public class StateWeeklyCondition {

    @SerializedName("time_of_day")
    @Expose
    String timeOfDay;

    @SerializedName("day_of_week")
    @Expose
    String dayOfWeek;

    @SerializedName("condition_id")
    @Expose
    String conditionId;

    @SerializedName("next_trigger")
    @Expose
    String nextTrigger;

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getNextTrigger() {
        return nextTrigger;
    }

    public void setNextTrigger(String nextTrigger) {
        this.nextTrigger = nextTrigger;
    }
}
