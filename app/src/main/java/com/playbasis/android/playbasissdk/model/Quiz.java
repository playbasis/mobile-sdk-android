
package com.playbasis.android.playbasissdk.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz {

    @SerializedName("quiz_id")
    @Expose
    private String quizId;

    @Expose
    private Grades grade;

    @Expose
    private Integer value;
    @SerializedName("total_completed_questions")
    @Expose
    private Integer totalCompletedQuestions;

    /**
     *
     * @return
     *     The grade
     */
    public Grades getGrade() {
        return grade;
    }

    /**
     *
     * @param grade
     *     The grade
     */
    public void setGrade(Grades grade) {
        this.grade = grade;
    }

    /**
     *
     * @return
     *     The value
     */
    public Integer getValue() {
        return value;
    }

    /**
     *
     * @param value
     *     The value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     *
     * @return
     *     The totalCompletedQuestions
     */
    public Integer getTotalCompletedQuestions() {
        return totalCompletedQuestions;
    }

    /**
     *
     * @param totalCompletedQuestions
     *     The totalCompletedQuestions
     */
    public void setTotalCompletedQuestions(Integer totalCompletedQuestions) {
        this.totalCompletedQuestions = totalCompletedQuestions;
    }

    /**
     *
     * @return
     *     The quizId
     */
    public String getQuizId() {
        return quizId;
    }

    /**
     *
     * @param quizId
     *     The quiz_id
     */
    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

}
