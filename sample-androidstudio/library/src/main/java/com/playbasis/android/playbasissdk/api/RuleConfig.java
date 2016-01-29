package com.playbasis.android.playbasissdk.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.model.RuleReward;

import java.util.List;

/**
 * Created by TorIsHere on 1/28/2016 AD.
 */
public class RuleConfig {

    @SerializedName("loop")
    @Expose
    boolean isLoop;

    @SerializedName("group_container")
    @Expose
    List<RuleReward> rewards;

    @SerializedName("group_id")
    @Expose
    String groupId;

    public boolean isLoop() {
        return isLoop;
    }

    public void setIsLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    public List<RuleReward> getRewards() {
        return rewards;
    }

    public void setRewards(List<RuleReward> rewards) {
        this.rewards = rewards;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
