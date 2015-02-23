package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/23/15.
 * For PlayBasisSdk project.
 */
public class QuizQuestionAnswer {
    public static final String TAG = "QuizQuestionAnswer";

    @Expose
    private List<QuizQuestionOption> options = new ArrayList<>();

    @Expose
    private Integer score;
    @SerializedName("max_score")
    @Expose
    private String maxScore;
    @Expose
    private String explanation;
    @SerializedName("total_score")
    @Expose
    private Integer totalScore;
    @SerializedName("total_max_score")
    @Expose
    private Integer totalMaxScore;
    @Expose
    private Grade grade;
    @Expose
    private List<Reward> rewards = new ArrayList<Reward>();

    public List<QuizQuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuizQuestionOption> options) {
        this.options = options;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalMaxScore() {
        return totalMaxScore;
    }

    public void setTotalMaxScore(Integer totalMaxScore) {
        this.totalMaxScore = totalMaxScore;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}
