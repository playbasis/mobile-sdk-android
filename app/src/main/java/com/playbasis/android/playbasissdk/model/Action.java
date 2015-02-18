package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.helper.DateHelper;

import java.util.Date;

/**
 * Created by gregoire barret on 2/18/15.
 * For PlayBasisSdk project.
 */
public class Action {
    public static final String TAG = "Action";
    @SerializedName("action_id")
    @Expose
    private String actionId;
    @SerializedName("action_name")
    @Expose
    private String actionName;
    @Expose
    private String time;
    @Expose
    private Integer count;

    /**
     *
     * @return
     *     The actionId
     */
    public String getActionId() {
        return actionId;
    }
    /**
     *
     * @param actionId
     *     The actionId
     */
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public Action withActionId(String actionId) {
        this.actionId = actionId;
        return this;
    }
    /**
     *
     * @return
     *     The actionName
     */
    public String getActionName() {
        return actionName;
    }
    /**
     *
     * @param actionName
     *     The actionName
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Action withActionName(String actionName) {
        this.actionName = actionName;
        return this;
    }
    /**
     *
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }
    /**
     *
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    public Action withTime(String time) {
        this.time = time;
        return this;
    }
    /**
     *
     * @return
     *     The time
     */
    public Date getTimeDate(){
        return DateHelper.stringToDate(time);
        
    }
    /**
     *
     * @param time
     *     The time
     */
    public void setTime(Date time) {
        this.time = DateHelper.dateToString(time);
    }

    public Action withTime(Date time) {
        this.time = DateHelper.dateToString(time);
        return this;
    }

    /**
     *
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }
    /**
     *
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    public Action withCount(Integer count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionId='" + actionId + '\'' +
                ", actionName='" + actionName + '\'' +
                ", time='" + time + '\'' +
                ", count=" + count +
                '}';
    }
}
