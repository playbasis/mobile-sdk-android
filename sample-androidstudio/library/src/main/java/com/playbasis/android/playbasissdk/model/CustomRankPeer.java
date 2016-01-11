package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick-Playbasis on 1/8/2016.
 */
public class CustomRankPeer {
    @SerializedName("name")
    @Expose
    String nodeName;

    String customRankName;
    int customRankValue;
    int previousRankValue;
    double percentChange;

    public CustomRankPeer() {
        this.nodeName = "";
        this.customRankName = "";
        this.customRankValue = 0;
        this.previousRankValue = 0;
        this.percentChange = 0;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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
    public int getPreviousValue() {
        return previousRankValue;
    }
    public void setPreviousValue(int previousValue) {
        this.previousRankValue = previousValue;
    }
    public double getPercentChange() {
        return percentChange;
    }
    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }
}
