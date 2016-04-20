package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;
import com.playbasis.android.playbasissdk.helper.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kittikorn Ariyasuk on 10/15/2015 AD.
 */
public class RuleDetail {
    public static final String TAG = "RuleDetails";

    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String tags;
    @SerializedName("active_status")
    @Expose
    private boolean activeStatus;
    @Expose
    private Action action;
    @Expose
    private RuleReward reward;

    private RuleGroup ruleGroup;
    private List<Condition> conditions;
    private List<RuleReward> rewards;


    /**
     *
     * @return name
     *
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return tags
     */
    public String getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     *
     * @return activeStatus
     */
    public boolean getActiveStatus() {
        return activeStatus;
    }

    /**
     *
     * @param activeStatus
     */
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    /**
     *
     * @return action
     */
    public Action getAction() {
        return action;
    }

    /**
     *
     * @param action
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     *
     * @return activeStatus
     */
    public boolean isActiveStatus() {
        return activeStatus;
    }

    /**
     *
     * @return rewards
     */
    public List<RuleReward> getRewards() {
        return rewards;
    }

    /**
     *
     * @param rewards
     */
    public void setRewards(List<RuleReward> rewards) {
        this.rewards = rewards;
    }

    /**
     *
     * @return conditions
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     *
     * @param conditions
     */
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public RuleGroup getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(RuleGroup ruleGroup) {
        this.ruleGroup = ruleGroup;
    }

    public static RuleDetail parseEngineRuleDetail(JSONObject json) throws JSONException {
        RuleDetail ruleDetail = new RuleDetail();

        ruleDetail.setActiveStatus(json.getBoolean("active_status"));
        ruleDetail.setDescription(json.getString("description"));
        ruleDetail.setName(json.getString(ApiConst.NAME));
        ruleDetail.setTags(json.getString("tags"));

        JSONArray jigsawSet = json.getJSONArray("jigsaw_set");
        List<Condition> ruleCondition = new ArrayList<>();
        for (int i = 0; i < jigsawSet.length(); i++) {
            String category = jigsawSet.getJSONObject(i).getString(ApiConst.CATEGORY);
            if (category.equals("ACTION")) {
                ruleDetail.setAction(JsonHelper.FromJsonObject(jigsawSet.getJSONObject(i).getJSONObject("config"), Action.class));
            } else if (category.equals("GROUP")) {
                ruleDetail.setRuleGroup(JsonHelper.FromJsonObject(jigsawSet.getJSONObject(i), RuleGroup.class));

                JSONArray rewardJSONArray = jigsawSet.getJSONObject(i).getJSONObject("config").getJSONArray("group_container");
                List<RuleReward> rewardList = new ArrayList<>();

                for (int j = 0; j < rewardJSONArray.length(); j++) {
                    //rewardList.add(JsonHelper.FromJsonObject(rewardJSONArray.getJSONObject(j), RuleReward.class));
                    rewardList.add(RuleReward.parseRuleReward(rewardJSONArray.getJSONObject(j)));
                }
                ruleDetail.setRewards(rewardList);
            } else if (category.equals("CONDITION")) {
                String type = jigsawSet.getJSONObject(i).getString(ApiConst.NAME);
                if (type.equals(ApiConst.REDEEM)) {
                    ruleCondition.add(RedeemCondition.parseRedeemCondition(jigsawSet.getJSONObject(i)));
                } else if (type.equals(("before"))) {
                    ruleCondition.add(BeforeCondition.parseBeforeCondition(jigsawSet.getJSONObject(i)));
                } else if (type.equals("after")) {
                    ruleCondition.add(AfterCondition.parseAfterCondition(jigsawSet.getJSONObject(i)));
                } else if (type.equals("between")) {
                    ruleCondition.add(BetweenCondition.parseBetweenCondition(jigsawSet.getJSONObject(i)));
                } else if (type.equals("cooldown")) {
                    ruleCondition.add(CoolDownCondition.parseCoolDownCondition(jigsawSet.getJSONObject(i)));
                } else if (type.equals("counter")) {
                    ruleCondition.add(CounterCondition.parseCounterCondition(jigsawSet.getJSONObject(i)));
                } else if (type.equals("weekly")) {
                    ruleCondition.add(WeeklyCondition.parseWeeklyCondition(jigsawSet.getJSONObject(i)));
                }
            }
        }
        ruleDetail.setConditions(ruleCondition);

        return ruleDetail;
    }
}
