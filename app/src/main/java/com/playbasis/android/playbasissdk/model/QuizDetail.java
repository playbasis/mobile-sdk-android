package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.helper.DateHelper;
import com.playbasis.android.playbasissdk.helper.StringHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class QuizDetail extends Quiz {
    public static final String TAG = "QuizDetail";


    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_expire")
    @Expose
    private String dateExpire;
    @Expose
    private Boolean status;
    @Expose
    private Boolean deleted;
    @Expose
    private List<Grade> grades = new ArrayList<Grade>();

    @SerializedName("total_max_score")
    @Expose
    private Integer totalMaxScore;
    @SerializedName("total_questions")
    @Expose
    private Integer totalQuestions;
    @Expose
    private Integer questions;
    @SerializedName("total_score")
    @Expose
    private Integer totalScore;

    @SerializedName("date_join")
    @Expose
    private String dateJoin;




    /**
     * return the date of start in string format.
     * @return
     *     The dateStart
     */
    public String getDateStart() {
        return dateStart;
    }
    
    public Date  getDateDateStart(){
        return DateHelper.stringToDate(dateStart);
        
    }

    /**
     * return the date of start in date format.
     * @param dateStart
     *     The date_start
     */
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * return the date of expire in string format
     * @return
     *     The dateExpire
     */
    public String getDateExpire() {
        return dateExpire;
    }
    
    public Date getDateDateExpire(){
        return DateHelper.stringToDate(dateExpire);
        
    }

    /**
     * return the date of expire in date format.
     * @param dateExpire
     *     The date_expire
     */
    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }

    /**
     *
     * @return
     *     The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     *
     * @param deleted
     *     The deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     *
     * @return
     *     The grades
     */
    public List<Grade> getGrades() {
        return grades;
    }

    /**
     *
     * @param grades
     *     The grades
     */
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }



    /**
     *
     * @return
     *     The totalMaxScore
     */
    public Integer getTotalMaxScore() {
        return totalMaxScore;
    }

    /**
     *
     * @param totalMaxScore
     *     The total_max_score
     */
    public void setTotalMaxScore(Integer totalMaxScore) {
        this.totalMaxScore = totalMaxScore;
    }

    /**
     *
     * @return
     *     The totalQuestions
     */
    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    /**
     *
     * @param totalQuestions
     *     The total_questions
     */
    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     *
     * @return
     *     The questions
     */
    public Integer getQuestions() {
        return questions;
    }

    /**
     *
     * @param questions
     *     The questions
     */
    public void setQuestions(Integer questions) {
        this.questions = questions;
    }

    /**
     *
     * @return
     *     The totalScore
     */
    public Integer getTotalScore() {
        return totalScore;
    }

    /**
     *
     * @param totalScore
     *     The total_score
     */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }



    /**
     *
     * @return
     *     The dateJoin
     */
    public String getDateJoin() {
        return dateJoin;
    }

    /**
     *
     * @param dateJoin
     *     The date_join
     */
    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }

    @Override
    public String toString() {
        return "QuizDetail{" +
                "dateStart='" + dateStart + '\'' +
                ", dateExpire='" + dateExpire + '\'' +
                ", status=" + status +
                ", deleted=" + deleted +
                ", grades=" + grades +
                ", totalMaxScore=" + totalMaxScore +
                ", totalQuestions=" + totalQuestions +
                ", questions=" + questions +
                ", totalScore=" + totalScore +
                ", dateJoin='" + dateJoin + '\'' +
                '}';
    }
}
