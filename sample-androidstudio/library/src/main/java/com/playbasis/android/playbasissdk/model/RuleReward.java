package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.helper.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TorIsHere on 10/16/2015 AD.
 */
public class RuleReward {
    @Expose
    private String quantity;
    @SerializedName("reward_name")
    @Expose
    private String name;
    @SerializedName("reward_id")
    @Expose
    private String rewardId;
    @SerializedName("item_id")
    @Expose
    private String itemId;

    private BadgeData badgeData;
    private Goods good;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public static RuleReward parseRuleReward(JSONObject json) throws JSONException {
        RuleReward ruleReward = new RuleReward();
        String name = json.getString("reward_name");
        ruleReward.setName(name);
        ruleReward.setItemId(json.getString("item_id"));
        ruleReward.setQuantity(json.getString("quantity"));
        ruleReward.setRewardId(json.getString("reward_id"));

        JSONObject data = json.getJSONObject("data");
        if (name.equals("goods")) {
            ruleReward.setGood(JsonHelper.FromJsonObject(data, Goods.class));
        } else if (name.equals("badge")) {
            ruleReward.setBadgeData(JsonHelper.FromJsonObject(data, BadgeData.class));
        }

        return ruleReward;
    }

}
