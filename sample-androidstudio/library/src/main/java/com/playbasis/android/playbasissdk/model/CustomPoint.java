package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TorIsHere on 2/1/2016 AD.
 */
public class CustomPoint {
    @SerializedName("custom_id")
    @Expose
    private String customId;

    @SerializedName("custom_name")
    @Expose
    private String customName;

    @SerializedName("custom_value")
    @Expose
    private String customValue;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomValue() {
        return customValue;
    }

    public void setCustomValue(String customValue) {
        this.customValue = customValue;
    }
}
