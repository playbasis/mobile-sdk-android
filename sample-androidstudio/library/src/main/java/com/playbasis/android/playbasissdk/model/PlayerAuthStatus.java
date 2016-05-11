package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

/**
 * Created by TorIsHere on 1/5/2016 AD.
 */
public class PlayerAuthStatus {

    @SerializedName("cl_player_id")
    @Expose
    private String playerId;

    @SerializedName(ApiConst.SESSION_ID)
    @Expose
    private String sessionId;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
