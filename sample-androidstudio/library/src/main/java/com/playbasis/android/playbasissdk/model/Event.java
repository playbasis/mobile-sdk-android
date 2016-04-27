package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;
import com.playbasis.android.playbasissdk.helper.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Event {
    public static final String TAG = "Event";
    
    @SerializedName("event_type")
    @Expose
    private String type;
    @SerializedName("event_message")
    @Expose
    private String message;
    @SerializedName("event_status")
    @Expose
    private Boolean status;

    @SerializedName("quest_id")
    @Expose
    private String questId;

    @SerializedName("reward_type")
    @Expose
    private String rewardType;
    @Expose
    private String value;
    @Expose
    private  int index;
    private BadgeData badgeData;
    private Goods good;

    /**
     *  
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     *  
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     *  
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     *  
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *  
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     *  
     * @param status the status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     *  
     * @return the quest Id
     */
    public String getQuestId() {
        return questId;
    }

    /**
     *  
     * @param questId the quest Id
     */
    public void setQuestId(String questId) {
        this.questId = questId;
    }

    /**
     *
     * @return the Reward type
     */
    public String getRewardType() {
        return rewardType;
    }

    /**
     *
     * @param rewardType the reward type
     */
    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    /**
     *  
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @param index the index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", questId='" + questId + '\'' +
                ", value='" + value + '\'' +
                ", index='" + index + '\'' +
                '}';
    }

    public BadgeData getBadgeData() {
        return badgeData;
    }

    public void setBadgeData(BadgeData badgeData) {
        this.badgeData = badgeData;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public static Event parseEvent(JSONObject jsonObject) throws JSONException{
        Event event = new Event();

        if (jsonObject.has("event_type")) {
            event.setType(jsonObject.getString("event_type"));
        }

        if (jsonObject.has("value")) {
            event.setValue(jsonObject.getString("value"));
        }

        if (jsonObject.has("index")) {
            event.setIndex(jsonObject.getInt("index"));
        }

        if (jsonObject.has("reward_type")) {
            event.setRewardType(jsonObject.getString("reward_type"));
        }

        if (jsonObject.has("event_status")) {
            event.setStatus(jsonObject.getBoolean("event_status"));
        }

        if (jsonObject.has("reward_data")) {
            JSONObject data =  jsonObject.getJSONObject("reward_data");
            if (jsonObject.getString("reward_type").equals(ApiConst.BADGE)) {
                event.setBadgeData(JsonHelper.FromJsonObject(data, BadgeData.class));
            }
            if (jsonObject.getString("reward_type").equals(ApiConst.GOODS)) {
                event.setGood(JsonHelper.FromJsonObject(data, Goods.class));
            }
        }
        return event;
    }
}


