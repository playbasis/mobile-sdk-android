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

    /**
     *  
     * @return the question
     */
    
    public String getQuestion() {
        return question;
    }

    /**
     *  
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }


    /**
     *  
     * @return image question url
     */
    public String getQuestionImage() {
        return questionImage;
    }

    /**
     *  
     * @param questionImage image question url
     */
    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    /**
     *  
     * @return list question options
     */
    public List<QuizQuestionOption> getOptions() {
        return options;
    }

    /**
     *  
     * @param options list question option
     */
    public void setOptions(List<QuizQuestionOption> options) {
        this.options = options;
    }

    /**
     *  
     * @return the integer
     */
    public Integer getInteger() {
        return integer;
    }

    /**
     *  
     * @param integer the integer
     */
    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    /**
     *  
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *  
     * @param total total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *  
     * @return the question id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     *
     * @param questionId the question id
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "question='" + question + '\'' +
                ", questionImage='" + questionImage + '\'' +
                ", options=" + options +
                ", integer=" + integer +
                ", total=" + total +
                ", questionId='" + questionId + '\'' +
                '}';
    }
}
