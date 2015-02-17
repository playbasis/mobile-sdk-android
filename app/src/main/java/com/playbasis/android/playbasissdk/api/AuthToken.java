package com.playbasis.android.playbasissdk.api;

import com.playbasis.android.playbasissdk.helper.DateHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class AuthToken {
    public static final String TAG = "Token";
    
    public String token;
    public Date dateExpire;

    public AuthToken(String token, Date dateExpire) {
        this.token = token;
        this.dateExpire = dateExpire;
    }

    public AuthToken(JSONObject jsonObject) throws JSONException {
        this.token = jsonObject.getString("token");
        this.dateExpire = DateHelper.stringToDate(jsonObject.getString("date_expire"));
    }

    public String getToken() {
        return token;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }
}
