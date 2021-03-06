
package com.playbasis.android.playbasissdk.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mission {

    @SerializedName("mission_name")
    @Expose
    private String missionName;
    @SerializedName("mission_number")
    @Expose
    private String missionNumber;
    @Expose
    private String description;
    @Expose
    private String hint;
    @Expose
    private String image;
    @Expose
    private List<Completion> completion = new ArrayList<Completion>();
    @Expose
    private List<Reward> rewards = new ArrayList<Reward>();
    @Expose
    private List<Event> events = new ArrayList<Event>();
    @SerializedName("mission_id")
    @Expose
    private String missionId;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @Expose
    private String status;
    @Expose
    private List<Pending> pending = new ArrayList<Pending>();

    /**
     * 
     * @return
     *     The missionName
     */
    public String getMissionName() {
        return missionName;
    }

    /**
     * 
     * @param missionName
     *     The mission_name
     */
    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }


    /**
     * 
     * @return
     *     The missionNumber
     */
    public String getMissionNumber() {
        return missionNumber;
    }

    /**
     * 
     * @param missionNumber
     *     The mission_number
     */
    public void setMissionNumber(String missionNumber) {
        this.missionNumber = missionNumber;
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


    /**
     * 
     * @return
     *     The completion
     */
    public List<Completion> getCompletion() {
        return completion;
    }

    /**
     * 
     * @param completion
     *     The completion
     */
    public void setCompletion(List<Completion> completion) {
        this.completion = completion;
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


    /**
     * 
     * @return
     *     The missionId
     */
    public String getMissionId() {
        return missionId;
    }

    /**
     * 
     * @param missionId
     *     The mission_id
     */
    public void setMissionId(String missionId) {
        this.missionId = missionId;
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


    /**
     * 
     * @return
     *     The pending
     */
    public List<Pending> getPending() {
        return pending;
    }

    /**
     * 
     * @param pending
     *     The pending
     */
    public void setPending(List<Pending> pending) {
        this.pending = pending;
    }


    /**
     *  
     * @return the list of events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     *
     * @param events the list of events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "missionName='" + missionName + '\'' +
                ", missionNumber='" + missionNumber + '\'' +
                ", description='" + description + '\'' +
                ", hint='" + hint + '\'' +
                ", image='" + image + '\'' +
                ", completion=" + completion +
                ", rewards=" + rewards +
                ", missionId='" + missionId + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", status='" + status + '\'' +
                ", pending=" + pending +
                '}';
    }
}
