package com.playbasis.android.playbasissdk.model;

import android.util.Log;

import com.google.gson.annotations.Expose;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.text.DateFormat;
/**
 * Created by TorIsHere on 10/19/2015 AD.
 */
public class AfterCondition extends Condition {

    @Expose
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static AfterCondition parseAfterCondition(JSONObject conditionJSON) throws JSONException {
        AfterCondition afterCondition = new AfterCondition();

        afterCondition.setName(conditionJSON.getString("name"));
        afterCondition.setCategory(conditionJSON.getString("category"));
        afterCondition.setDescription(conditionJSON.getString("description"));
        afterCondition.setId(conditionJSON.getString("id"));
        afterCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        try {
            String dateTime = conditionJSON.getJSONObject("config").getString("timestamp");
            DateFormat format = new SimpleDateFormat("mm/dd/yyyy hh:mm", Locale.ENGLISH);
            Date date = format.parse(dateTime);
            afterCondition.setTimestamp(date);
        } catch (ParseException e) {
            Log.d("Engine Rule Condition", e.getMessage());
        }

        return afterCondition;
    }
}
