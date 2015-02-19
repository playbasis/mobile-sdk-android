package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Grades extends AbstractGrade {
    public static final String TAG = "Grades";

    @Expose
    private List<Reward> rewards = new ArrayList<Reward>();


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

}
