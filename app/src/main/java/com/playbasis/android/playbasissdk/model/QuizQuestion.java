package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class QuizQuestion {
    public static final String TAG = "QuizQuestion";
    
    @Expose
    private String question;
    @Expose
    @SerializedName("question_image")
    private String questionImage;
    @Expose
    private List<QuizQuestionOption> options;
    @Expose
    private Integer integer;
    @Expose
    private Integer total;
    @SerializedName("question_id")
    @Expose
    private String questionId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public List<QuizQuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuizQuestionOption> options) {
        this.options = options;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
