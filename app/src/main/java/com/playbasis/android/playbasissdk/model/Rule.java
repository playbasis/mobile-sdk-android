package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class Rule {
    public static final String TAG = "Rules";
    
    @Expose
    private List<Event> events;
    @SerializedName("events_missions")
    @Expose
    private List<Mission> missions;
    @SerializedName("events_quests")
    @Expose
    private List<Quest> quests;


    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }
}
