package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TorIsHere on 1/6/2016 AD.
 */
public class SaleReport {

    @SerializedName("node_id")
    @Expose
    private String nodeId;

    @Expose
    private String name;

    @Expose
    private float amount;

    @SerializedName("previous_amount")
    @Expose
    private float previousAmount;

    @SerializedName("percent_changed")
    @Expose
    private float percentChanged;

    public SaleReport() {
        this.nodeId = "";
        this.name = "";
        this.amount = 0.0f;
        this.previousAmount = 0.0f;
        this.percentChanged = 0.0f;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(float previousAmount) {
        this.previousAmount = previousAmount;
    }

    public float getPercentChanged() {
        return percentChanged;
    }

    public void setPercentChanged(float percentChanged) {
        this.percentChanged = percentChanged;
    }
}
