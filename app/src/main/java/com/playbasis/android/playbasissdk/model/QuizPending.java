package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gregoire barret on 2/23/15.
 * For PlayBasisSdk project.
 */
public class QuizPending {
    public static final String TAG = "QuizPending";
    
    @Expose
    private Integer value;
    
    @SerializedName("total_completed_questions")
    @Expose
    private Integer totalCompletedQuestions; 
    
    @SerializedName("total_pending_questions")
    @Expose
    private Integer totalPendingQuestions;
    
    @SerializedName("quiz_id")
    @Expose
    private String quizId;

    @Expose
    private Grades grade;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getTotalCompletedQuestions() {
        return totalCompletedQuestions;
    }

    public void setTotalCompletedQuestions(Integer totalCompletedQuestions) {
        this.totalCompletedQuestions = totalCompletedQuestions;
    }

    public Integer getTotalPendingQuestions() {
        return totalPendingQuestions;
    }

    public void setTotalPendingQuestions(Integer totalPendingQuestions) {
        this.totalPendingQuestions = totalPendingQuestions;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public Grades getGrade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }
}
