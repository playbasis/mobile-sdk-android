
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pending {

    @SerializedName("event_type")
    @Expose
    private String eventType;
    @Expose
    private String message;
    @Expose
    private Incomplete incomplete;

    /**
     * 
     * @return
     *     The eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * 
     * @param eventType
     *     The event_type
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Pending withEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Pending withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 
     * @return
     *     The incomplete
     */
    public Incomplete getIncomplete() {
        return incomplete;
    }

    /**
     * 
     * @param incomplete
     *     The incomplete
     */
    public void setIncomplete(Incomplete incomplete) {
        this.incomplete = incomplete;
    }

    public Pending withIncomplete(Incomplete incomplete) {
        this.incomplete = incomplete;
        return this;
    }


    @Override
    public String toString() {
        return "Pending{" +
                "eventType='" + eventType + '\'' +
                ", message='" + message + '\'' +
                ", incomplete=" + incomplete +
                '}';
    }
}
