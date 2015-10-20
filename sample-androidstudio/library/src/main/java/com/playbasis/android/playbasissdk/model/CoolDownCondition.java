package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TorIsHere on 10/20/2015 AD.
 */
public class CoolDownCondition extends Condition {

    @Expose
    @SerializedName("cooldown")
    private int coolDown;

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public static CoolDownCondition parseCoolDownCondition(JSONObject conditionJSON) throws JSONException {
        CoolDownCondition coolDownCondition = new CoolDownCondition();

        coolDownCondition.setName(conditionJSON.getString("name"));
        coolDownCondition.setCategory(conditionJSON.getString("category"));
        coolDownCondition.setDescription(conditionJSON.getString("description"));
        coolDownCondition.setId(conditionJSON.getString("id"));
        coolDownCondition.setSortOrder(conditionJSON.getInt("sort_order"));
        coolDownCondition.setCoolDown(conditionJSON.getJSONObject("config").getInt("cooldown"));

        return coolDownCondition;
    }

}
