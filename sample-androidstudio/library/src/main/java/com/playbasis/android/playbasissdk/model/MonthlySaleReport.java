package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TorIsHere on 1/7/2016 AD.
 */
public class MonthlySaleReport {

    String month;

    String year;

    @Expose
    float amount;

    @SerializedName("previous_amount")
    @Expose
    float previousAmount;

    @SerializedName("percent_changed")
    @Expose
    float percentChanged;

    public MonthlySaleReport() {
        this.month = "";
        this.year = "";
        this.amount = 0.0f;
        this.previousAmount = 0.0f;
        this.percentChanged = 0.0f;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
