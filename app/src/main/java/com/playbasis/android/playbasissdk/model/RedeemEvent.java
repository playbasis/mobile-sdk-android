package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    

    private List<TokenValue> incomplete;
    
    private Integer incompleteValue;
    
    @SerializedName("event_type")
    @Expose
    private  String eventType;

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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getIncompleteValue() {
        return incompleteValue;
    }

    public void setIncompleteValue(Integer incompleteValue) {
        this.incompleteValue = incompleteValue;
    }
}
