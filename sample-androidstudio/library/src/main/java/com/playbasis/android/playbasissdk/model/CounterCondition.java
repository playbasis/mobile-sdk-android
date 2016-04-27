package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TorIsHere on 10/20/2015 AD.
 */
public class CounterCondition extends Condition {

    @Expose
    @SerializedName("counter_value")
    private int counterValue;

    @Expose
    private int interval;

    @Expose
    @SerializedName("interval_unit")
    private String intervalUnit;

    @Expose
    @SerializedName("reset_timeout")
    private boolean resetTimeout;

    public int getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(int counterValue) {
        this.counterValue = counterValue;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public boolean isResetTimeout() {
        return resetTimeout;
    }

    public void setResetTimeout(boolean resetTimeout) {
        this.resetTimeout = resetTimeout;
    }

    public static CounterCondition parseCounterCondition(JSONObject conditionJSON) throws JSONException {
        CounterCondition counterCondition = new CounterCondition();

        counterCondition.setName(conditionJSON.getString(ApiConst.NAME));
        counterCondition.setCategory(conditionJSON.getString(ApiConst.CATEGORY));
        counterCondition.setDescription(conditionJSON.getString("description"));
        counterCondition.setId(conditionJSON.getString(ApiConst.ID));
        counterCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        JSONObject configJSONObject = conditionJSON.getJSONObject("config");
        counterCondition.setCounterValue(configJSONObject.getInt("counter_value"));
        counterCondition.setInterval(configJSONObject.getInt("interval"));
        counterCondition.setIntervalUnit(configJSONObject.getString("interval_unit"));
        counterCondition.setResetTimeout(configJSONObject.getBoolean("reset_timeout"));

        return counterCondition;
    }
}
