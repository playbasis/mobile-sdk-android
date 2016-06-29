
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MissionInfo extends Mission {

    @SerializedName("quest_id")
    @Expose
    private String questId;

    /**
     * 
     * @return
     *     The quest id
     */
    public String getQuestId() {
        return questId;
    }

    /**
     * 
     * @param quest id
     *     The quest id
     */
    public void setQuestId(String questId) {
        this.questId = questId;
    }

    @Override
    public String toString() {
        return "MissionInfo{" +
                "mission=" + super.toString() +
                ", questId='" + questId + '\'' +
                '}';
    }
}
