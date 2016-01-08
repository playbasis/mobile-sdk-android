package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TorIsHere on 1/7/2016 AD.
 */
public class CustomRank {
    @SerializedName("cl_player_id")
    @Expose
    String playerId;

    String customRankName;
    int customRankValue;

    public CustomRank() {
        this.playerId = "";
        this.customRankName = "";
        this.customRankValue = 0;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getCustomRankName() {
        return customRankName;
    }

    public void setCustomRankName(String customRankName) {
        this.customRankName = customRankName;
    }

    public int getCustomRankValue() {
        return customRankValue;
    }

    public void setCustomRankValue(int customRankValue) {
        this.customRankValue = customRankValue;
    }
}
