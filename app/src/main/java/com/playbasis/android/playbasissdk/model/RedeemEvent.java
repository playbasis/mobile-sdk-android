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

    /**
     *  
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *  
     * @return imcomplete event
     */
    public List<TokenValue> getIncomplete() {
        return incomplete;
    }

    /**
     *  
     * @param incomplete incomplete event
     */
    public void setIncomplete(List<TokenValue> incomplete) {
        this.incomplete = incomplete;
    }

    /**
     *  
     * @return event type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     *  
     * @param eventType event type
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     *  
     * @return incomplete value
     */
    public Integer getIncompleteValue() {
        return incompleteValue;
    }

    /**
     *
     * @param incompleteValue incomplete value
     */
    public void setIncompleteValue(Integer incompleteValue) {
        this.incompleteValue = incompleteValue;
    }

    @Override
    public String toString() {
        return "RedeemEvent{" +
                "message='" + message + '\'' +
                ", incomplete=" + incomplete +
                ", incompleteValue=" + incompleteValue +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
