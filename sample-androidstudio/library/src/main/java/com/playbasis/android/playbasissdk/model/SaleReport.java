package com.playbasis.android.playbasissdk.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pongsakorn on 1/8/2016.
 */
public class SaleReport {

    String nodeId;
    String nodeName;
    Integer amount;
    Integer previousAmount;
    double percentChanged;

    public SaleReport() {
        this.nodeId = "";
        this.nodeName = "";
        this.amount = 0;
        this.previousAmount = 0;
        this.percentChanged = 0;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(Integer previousAmount) {
        this.previousAmount = previousAmount;
    }

    public double getPercentChanged() {
        return percentChanged;
    }

    public void setPercentChanged(double percentChanged) {
        this.percentChanged = percentChanged;
    }
}
