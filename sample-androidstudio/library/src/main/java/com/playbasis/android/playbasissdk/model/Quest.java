
package com.playbasis.android.playbasissdk.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quest {

    @SerializedName("quest_name")
    @Expose
    private String questName;
    @Expose
    private String description;
    @Expose
    private String hint;
    @Expose
    private String image;
    @Expose
    private String status;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @Expose
    private List<Mission> missions = new ArrayList<Mission>();
    @SerializedName("mission_order")
    @Expose
    private Boolean missionOrder;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @Expose
    private List<Reward> rewards = new ArrayList<Reward>();
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("quest_id")
    @Expose
    private String questId;
    @SerializedName("player_status")
    @Expose
    private String playerStatus;

    private String dateStart;
    private String dateEnd;

    public String getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(String playerStatus) {
        this.playerStatus = playerStatus;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * 
     * @return
     *     The questName
     */
    public String getQuestName() {
        return questName;
    }

    /**
     * 
     * @param questName
     *     The quest_name
     */
    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public Quest withQuestName(String questName) {
        this.questName = questName;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Quest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * 
     * @param hint
     *     The hint
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    public Quest withHint(String hint) {
        this.hint = hint;
        return this;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    public Quest withImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Quest withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * @return
     *     The sortOrder
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * 
     * @param sortOrder
     *     The sort_order
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Quest withSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    /**
     * 
     * @return
     *     The missions
     */
    public List<Mission> getMissions() {
        return missions;
    }

    /**
     * 
     * @param missions
     *     The missions
     */
    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public Quest withMissions(List<Mission> missions) {
        this.missions = missions;
        return this;
    }

    /**
     * 
     * @return
     *     The missionOrder
     */
    public Boolean getMissionOrder() {
        return missionOrder;
    }

    /**
     * 
     * @param missionOrder
     *     The mission_order
     */
    public void setMissionOrder(Boolean missionOrder) {
        this.missionOrder = missionOrder;
    }

    public Quest withMissionOrder(Boolean missionOrder) {
        this.missionOrder = missionOrder;
        return this;
    }

    /**
     * 
     * @return
     *     The dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * 
     * @param dateAdded
     *     The date_added
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Quest withDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    /**
     * 
     * @return
     *     The clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 
     * @param clientId
     *     The client_id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Quest withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * 
     * @return
     *     The siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * 
     * @param siteId
     *     The site_id
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Quest withSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    /**
     * 
     * @return
     *     The rewards
     */
    public List<Reward> getRewards() {
        return rewards;
    }

    /**
     * 
     * @param rewards
     *     The rewards
     */
    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public Quest withRewards(List<Reward> rewards) {
        this.rewards = rewards;
        return this;
    }

    /**
     * 
     * @return
     *     The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * 
     * @param dateModified
     *     The date_modified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Quest withDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    /**
     * 
     * @return
     *     The questId
     */
    public String getQuestId() {
        return questId;
    }

    /**
     * 
     * @param questId
     *     The quest_id
     */
    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public Quest withQuestId(String questId) {
        this.questId = questId;
        return this;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "questName='" + questName + '\'' +
                ", description='" + description + '\'' +
                ", hint='" + hint + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", missions=" + missions +
                ", missionOrder=" + missionOrder +
                ", dateAdded='" + dateAdded + '\'' +
                ", clientId='" + clientId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", rewards=" + rewards +
                ", dateModified='" + dateModified + '\'' +
                ", questId='" + questId + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                '}';
    }
}
