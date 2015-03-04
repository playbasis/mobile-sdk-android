
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reward {

    @SerializedName("reward_value")
    @Expose
    private String rewardValue;
    @SerializedName("reward_type")
    @Expose
    private String rewardType;
    @SerializedName("reward_id")
    @Expose
    private String rewardId;
    @SerializedName("reward_data")
    @Expose
    private BadgeData rewardData;

    /**
     * 
     * @return
     *     The rewardValue
     */
    public String getRewardValue() {
        return rewardValue;
    }

    /**
     * 
     * @param rewardValue
     *     The reward_value
     */
    public void setRewardValue(String rewardValue) {
        this.rewardValue = rewardValue;
    }

    public Reward withRewardValue(String rewardValue) {
        this.rewardValue = rewardValue;
        return this;
    }

    /**
     * 
     * @return
     *     The rewardType
     */
    public String getRewardType() {
        return rewardType;
    }

    /**
     * 
     * @param rewardType
     *     The reward_type
     */
    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Reward withRewardType(String rewardType) {
        this.rewardType = rewardType;
        return this;
    }

    /**
     * 
     * @return
     *     The rewardId
     */
    public String getRewardId() {
        return rewardId;
    }

    /**
     * 
     * @param rewardId
     *     The reward_id
     */
    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public Reward withRewardId(String rewardId) {
        this.rewardId = rewardId;
        return this;
    }

    /**
     * 
     * @return
     *     The rewardData
     */
    public BadgeData getRewardData() {
        return rewardData;
    }

    /**
     * 
     * @param rewardData
     *     The reward_data
     */
    public void setRewardData(BadgeData rewardData) {
        this.rewardData = rewardData;
    }

    public Reward withRewardData(BadgeData rewardData) {
        this.rewardData = rewardData;
        return this;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "rewardValue='" + rewardValue + '\'' +
                ", rewardType='" + rewardType + '\'' +
                ", rewardId='" + rewardId + '\'' +
                ", rewardData=" + rewardData +
                '}';
    }
}
