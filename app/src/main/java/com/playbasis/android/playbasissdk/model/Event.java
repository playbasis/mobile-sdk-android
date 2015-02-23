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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }
}


