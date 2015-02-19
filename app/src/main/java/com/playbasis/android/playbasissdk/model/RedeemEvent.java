package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class RedeemEvent {
    public static final String TAG = "RedeemEvent";
    
    @Expose
    private String message;
    
    @Expose
    private List<TokenValue> incomplete;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TokenValue> getIncomplete() {
        return incomplete;
    }

    public void setIncomplete(List<TokenValue> incomplete) {
        this.incomplete = incomplete;
    }

}
