package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick-Playbasis on 1/12/2016.
 */
public class CustomLeaderboard {
    @SerializedName(ApiConst.NAME)
    @Expose
    String nodeName;

    String rankedName;
    int rankedValue;
    int previousRankedValue;
    double percentChange;

    List<PlayerLeaderboard> players;

    public CustomLeaderboard() {
        this.nodeName = "";
        this.rankedName = "";
        this.rankedValue = 0;
        this.previousRankedValue = 0;
        this.percentChange = 0;
        this.players = new ArrayList<>();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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


    public List<PlayerLeaderboard> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerLeaderboard> players) {
        this.players = players;
    }
}
