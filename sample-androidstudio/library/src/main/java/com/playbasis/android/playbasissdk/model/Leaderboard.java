package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick-Playbasis on 1/11/2016.
 */
public class Leaderboard {
    @SerializedName("cl_player_id")
    @Expose
    String playerID;

    @Expose
    String image;

    @Expose
    String username;

    @SerializedName("first_name")
    @Expose
    String firstName;

    @SerializedName("last_name")
    @Expose
    String lastName;

    List<NodeLeaderboard> nodes;

    String rankedName;
    int rankedValue;
    int previousRankedValue;
    double percentChange;

    public Leaderboard() {
        this.playerID = "";
        this.image = "";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.rankedName = "";
        this.rankedValue = 0;
        this.previousRankedValue = 0;
        this.percentChange = 0;
        this.nodes = new ArrayList<>();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<NodeLeaderboard> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeLeaderboard> nodes) {
        this.nodes = nodes;
    }
}
