package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

/**
 * Created by TorIsHere on 10/30/2015 AD.
 */
public class ReferralCode {
    public static final String TAG = "ReferralCode";

    @Expose
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
