package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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


    /**
     *  
     * @return events list
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     *  
     * @param events event list
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * 
     * @return missions list
     */
    public List<Mission> getMissions() {
        return missions;
    }

    /**
     *  
     * @param missions mission list
     */
    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    /**
     *  
     * @return quest list
     */
    public List<Quest> getQuests() {
        return quests;
    }

    /**
     *
     * @param quests quest list
     */
    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "events=" + events +
                ", missions=" + missions +
                ", quests=" + quests +
                '}';
    }

    public static Rule parseRule(JSONObject json) throws JSONException {
        Rule rule = new Rule();
        JSONArray events = json.getJSONArray(ApiConst.EVENTS);
        List<Event> myEvents = new ArrayList<Event>();
        for (int i = 0; i < events.length(); i++) {
            JSONObject eventJSON = (JSONObject) events.get(i);
            System.out.println(eventJSON);
            myEvents.add(Event.parseEvent(eventJSON));
        }
        rule.setEvents(myEvents);
        // TODO setMissions and setQuest
        return rule;
    }
}
