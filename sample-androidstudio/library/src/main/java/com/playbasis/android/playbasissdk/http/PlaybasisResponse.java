package com.playbasis.android.playbasissdk.http;

import com.playbasis.android.playbasissdk.api.ApiConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class PlaybasisResponse {
    public static final String TAG = "PlaybasisResponse";

    public PlaybasisResponse(Object response, Boolean success, int errorCode, String message, long timestamp,
                             String time, String version) {
        this.response = response;
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
        this.time = time;
        this.version = version;
    }
    
    public PlaybasisResponse (JSONObject jsonObject) throws JSONException {
        toObject(jsonObject);
    }

    
    public void toObject(JSONObject jsonObject) throws JSONException {
        this.success = jsonObject.getBoolean("success");
        this.errorCode = jsonObject.getInt("error_code");
        this.message = jsonObject.getString(ApiConst.MESSAGE);
        this.timestamp = jsonObject.getLong("timestamp");
        this.time = jsonObject.getString(ApiConst.TIME);
        this.version = jsonObject.getString("version");
        if(jsonObject.get("response") instanceof JSONObject)
            this.response = jsonObject.getJSONObject("response");
        else if(jsonObject.get("response") instanceof JSONArray)
            this.response = jsonObject.getJSONArray("response");
        else if(jsonObject.get("response") instanceof Integer)
            this.response = String.valueOf(jsonObject.getInt("response"));
        else if(jsonObject.get("response") instanceof String)
            this.response = jsonObject.getString("response");
    }

    public Object response;


    public Boolean success;
    
    public int errorCode;
    
    public String message;
    
    public long timestamp;
    
    public String time;
    
    public String version;
}
