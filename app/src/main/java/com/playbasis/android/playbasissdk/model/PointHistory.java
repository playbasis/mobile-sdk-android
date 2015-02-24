package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/24/15.
 * For PlayBasisSdk project.
 */
public class PointHistory extends Point {
    public static final String TAG = "PointHistory";


    @Expose
    private String message;

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
        return "PointHistory{" +
                "message='" + message + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", player=" + player +
                ", actionName='" + actionName + '\'' +
                ", stringFilter=" + stringFilter +
                ", actionIcon='" + actionIcon + '\'' +
                ", badge=" + badge +
                '}';
    }
}
