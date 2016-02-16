package com.playbasis.android.playbasissdk.model;

import android.util.Log;

import com.google.gson.annotations.Expose;

import org.json.JSONArray;
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
public class BeforeCondition extends Condition {

    @Expose
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static BeforeCondition parseBeforeCondition(JSONObject conditionJSON) throws JSONException {
        BeforeCondition beforeCondition = new BeforeCondition();

        beforeCondition.setName(conditionJSON.getString("name"));
        beforeCondition.setCategory(conditionJSON.getString("category"));
        beforeCondition.setDescription(conditionJSON.getString("description"));
        beforeCondition.setId(conditionJSON.getString("id"));
        beforeCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        try {
            String dateTime = conditionJSON.getJSONObject("config").getString("timestamp");
            DateFormat format = new SimpleDateFormat("mm/dd/yyyy hh:mm", Locale.ENGLISH);
            Date date = format.parse(dateTime);
            beforeCondition.setTimestamp(date);
        } catch (ParseException e) {
            Log.d("Engine Rule Condition", e.getMessage());
        }

        return beforeCondition;
    }
}
