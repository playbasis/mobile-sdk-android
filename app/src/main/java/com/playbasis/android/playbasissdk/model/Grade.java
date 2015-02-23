package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Grade extends AbstractGrade {
    public static final String TAG = "Grade";

    @Expose
    private Rewards rewards;


    /**
     *
     * @return
     *     The rewards
     */
    public Rewards getRewards() {
        return rewards;
    }

    /**
     *
     * @param rewards
     *     The rewards
     */
    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }

}
