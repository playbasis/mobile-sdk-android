package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by TorIsHere on 1/28/2016 AD.
 */
public class Input {

    @SerializedName("loop")
    @Expose
    Boolean isLoop;

    @SerializedName("group_container")
    @Expose
    ArrayList<StateReward> stateRewards;

    @SerializedName("group_id")
    @Expose
    String groupId;

    @Expose
    Integer index;

    @SerializedName("break")
    @Expose
    boolean isBreak;

    public Boolean getIsLoop() {
        return isLoop;
    }

    public void setIsLoop(Boolean isLoop) {
        this.isLoop = isLoop;
    }

    public ArrayList<StateReward> getStateRewards() {
        return stateRewards;
    }

    public void setStateRewards(ArrayList<StateReward> stateRewards) {
        this.stateRewards = stateRewards;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public boolean isBreak() {
        return isBreak;
    }

    public void setIsBreak(boolean isBreak) {
        this.isBreak = isBreak;
    }
}
