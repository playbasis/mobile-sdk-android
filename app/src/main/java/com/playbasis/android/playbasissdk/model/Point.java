
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Point {


    @SerializedName("reward_id")
    @Expose
    private String rewardId;
    @SerializedName("reward_name")
    @Expose
    private String rewardName;
    @Expose
    private Integer value;

    /**
     *
     * @return
     * The rewardId
     */
    public String getRewardId() {
        return rewardId;
    }

    /**
     *
     * @param rewardId
     * The reward_id
     */
    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    /**
     *
     * @return
     * The rewardName
     */
    public String getRewardName() {
        return rewardName;
    }

    /**
     *
     * @param rewardName
     * The reward_name
     */
    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    /**
     *
     * @return
     * The value
     */
    public Integer getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Point{" +
                "rewardId='" + rewardId + '\'' +
                ", rewardName='" + rewardName + '\'' +
                ", value=" + value +
                '}';
    }
}
