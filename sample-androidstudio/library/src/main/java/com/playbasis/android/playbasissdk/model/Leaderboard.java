package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick-Playbasis on 1/11/2016.
 */
public class Leaderboard {
    @SerializedName("player_id")
    @Expose
    String playerID;

    String rankedName;
    int rankedValue;
    int previousRankedValue;
    double percentChange;

    public Leaderboard() {
        this.playerID = "";
        this.rankedName = "";
        this.rankedValue = 0;
        this.previousRankedValue = 0;
        this.percentChange = 0;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String nodeName) {
        this.playerID = nodeName;
    }

    public String getRankedName() {
        return rankedName;
    }

    public void setRankedName(String rankedName) {
        this.rankedName = rankedName;
    }

    public int getRankedValue() {
        return rankedValue;
    }
    public void setRankedValue(int value) {
        this.rankedValue = value;
    }
    public int getPreviousValue() {
        return previousRankedValue;
    }
    public void setPreviousValue(int previousValue) {
        this.previousRankedValue = previousValue;
    }
    public double getPercentChange() {
        return percentChange;
    }
    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }
}
