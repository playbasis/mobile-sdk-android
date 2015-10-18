package com.playbasis.android.playbasissdk.model;

import java.util.List;

/**
 * Created by ToR on 10/18/15.
 */
public class RedeemConditionn extends Condition{

    List<RuleReward> rewards;

    /**
     *
     * @return List<RuleReward> rewards
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
}
