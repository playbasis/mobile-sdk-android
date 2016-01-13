package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick-Playbasis on 1/8/2016.
 */
public class Sale {


    @SerializedName("node_id")
    @Expose
    String nodeId;

    @SerializedName("name")
    @Expose
    String nodeName;

    double percentChange;

    String parameterName;
    int currentValue;
    int previousValue;

    public Sale (){
        nodeId = "";
        nodeName = "";
        parameterName = "";
        currentValue = 0;
        previousValue = 0;
        percentChange = 0;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    public String getNodeId() {
        return this.nodeId;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getNodeName() {
        return this.nodeName;
    }
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
    public String getParameterName() {
        return this.parameterName;
    }
    public int getCurrentValue() {
        return currentValue;
    }
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
    public int getPreviousValue() {
        return previousValue;
    }
    public void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }
    public double getPercentChange() {
        return percentChange;
    }
    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }
}