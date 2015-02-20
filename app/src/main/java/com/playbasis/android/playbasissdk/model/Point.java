
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Point {


    @Expose
    private String message;
    @SerializedName("reward_id")
    @Expose
    private String rewardId;
    @SerializedName("reward_name")
    @Expose
    private String rewardName;
    @Expose
    private Integer value;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @Expose
    private Player player;
    @SerializedName("action_name")
    @Expose
    private String actionName;
    @SerializedName("string_filter")
    @Expose
    private Object stringFilter;
    @SerializedName("action_icon")
    @Expose
    private String actionIcon;
    @Expose
    private Badge badge;

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

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

    /**
     *
     * @return
     * The dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     *
     * @param dateAdded
     * The date_added
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     *
     * @return
     * The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @param player
     * The player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     *
     * @return
     * The actionName
     */
    public String getActionName() {
        return actionName;
    }

    /**
     *
     * @param actionName
     * The action_name
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     *
     * @return
     * The stringFilter
     */
    public Object getStringFilter() {
        return stringFilter;
    }

    /**
     *
     * @param stringFilter
     * The string_filter
     */
    public void setStringFilter(Object stringFilter) {
        this.stringFilter = stringFilter;
    }

    /**
     *
     * @return
     * The actionIcon
     */
    public String getActionIcon() {
        return actionIcon;
    }

    /**
     *
     * @param actionIcon
     * The action_icon
     */
    public void setActionIcon(String actionIcon) {
        this.actionIcon = actionIcon;
    }

    /**
     *
     * @return
     * The badge
     */
    public Badge getBadge() {
        return badge;
    }

    /**
     *
     * @param badge
     * The badge
     */
    public void setBadge(Badge badge) {
        this.badge = badge;
    }


    @Override
    public String toString() {
        return "Point{" +
                "message='" + message + '\'' +
                ", rewardId='" + rewardId + '\'' +
                ", rewardName='" + rewardName + '\'' +
                ", value=" + value +
                ", dateAdded='" + dateAdded + '\'' +
                ", player=" + player +
                ", actionName='" + actionName + '\'' +
                ", stringFilter=" + stringFilter +
                ", actionIcon='" + actionIcon + '\'' +
                ", badge=" + badge +
                '}';
    }
}
