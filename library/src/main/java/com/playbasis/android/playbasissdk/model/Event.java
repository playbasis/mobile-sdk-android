package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Event {
    public static final String TAG = "Event";
    
    @SerializedName("event_type")
    @Expose
    private String type;
    @SerializedName("event_message")
    @Expose
    private String message;
    @SerializedName("event_status")
    @Expose
    private Boolean status;

    @SerializedName("quest_id")
    @Expose
    private String questId;

    /**
     *  
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     *  
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     *  
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     *  
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *  
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     *  
     * @param status the status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     *  
     * @return the quest Id
     */
    public String getQuestId() {
        return questId;
    }

    /**
     *  
     * @param questId the quest Id
     */
    public void setQuestId(String questId) {
        this.questId = questId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", questId='" + questId + '\'' +
                '}';
    }
}


