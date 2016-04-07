package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.playbasis.android.playbasissdk.helper.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TorIsHere on 10/19/2015 AD.
 */
public class RedeemCondition extends Condition {

    @Expose
    List<RuleReward> rewards;

    public List<RuleReward> getRewards() {
        return rewards;
    }

    public void setRewards(List<RuleReward> rewards) {
        this.rewards = rewards;
    }

    public static RedeemCondition parseRedeemCondition(JSONObject conditionJSON) throws JSONException {
        RedeemCondition redeemCondition = new RedeemCondition();

        redeemCondition.setName(conditionJSON.getString("name"));
        redeemCondition.setCategory(conditionJSON.getString("category"));
        redeemCondition.setDescription(conditionJSON.getString("description"));
        redeemCondition.setId(conditionJSON.getString("id"));
        redeemCondition.setSortOrder(conditionJSON.getInt("sort_order"));

        JSONArray rewardJSONArray = conditionJSON.getJSONObject("config").getJSONArray("group_container");
        List<RuleReward> rewardsTemp = new ArrayList<>();

        for(int i = 0; i < rewardJSONArray.length(); i++) {
            rewardsTemp.add(RuleReward.parseRuleReward(rewardJSONArray.getJSONObject(i)));
        }
        redeemCondition.setRewards(rewardsTemp);
        return redeemCondition;
    }
}
