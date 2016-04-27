package com.playbasis.android.playbasissdk.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

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
public class BetweenCondition extends Condition{

    @Expose
    @SerializedName("start_time")
    private Date startTime;

    @Expose
    @SerializedName("end_time")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public static BetweenCondition parseBetweenCondition(JSONObject conditionJSON) throws JSONException {
        BetweenCondition betweenCondition = new BetweenCondition();

        betweenCondition.setName(conditionJSON.getString(ApiConst.NAME));
        betweenCondition.setCategory(conditionJSON.getString(ApiConst.CATEGORY));
        betweenCondition.setDescription(conditionJSON.getString("description"));
        betweenCondition.setId(conditionJSON.getString(ApiConst.ID));
        betweenCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        try {
            String dateTime = conditionJSON.getJSONObject("config").getString("start_time");
            DateFormat format = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
            betweenCondition.setStartTime(format.parse(dateTime));

            dateTime = conditionJSON.getJSONObject("config").getString("end_time");
            betweenCondition.setEndTime(format.parse(dateTime));

        } catch (ParseException e) {
            Log.d("Engine Rule Condition", e.getMessage());
        }

        return betweenCondition;
    }
}
