package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class QuizDetail extends Quiz {
    public static final String TAG = "QuizDetail";

    @Expose
    private String name;
    @Expose
    private String image;
    @Expose
    private String weight;
    @SerializedName("date_start")
    @Expose
    private Object dateStart;
    @SerializedName("date_expire")
    @Expose
    private Object dateExpire;
    @Expose
    private Boolean status;
    @Expose
    private String description;
    @SerializedName("description_image")
    @Expose
    private String descriptionImage;
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
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     *     The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     *     The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     *     The dateStart
     */
    public Object getDateStart() {
        return dateStart;
    }

    /**
     *
     * @param dateStart
     *     The date_start
     */
    public void setDateStart(Object dateStart) {
        this.dateStart = dateStart;
    }

    /**
     *
     * @return
     *     The dateExpire
     */
    public Object getDateExpire() {
        return dateExpire;
    }

    /**
     *
     * @param dateExpire
     *     The date_expire
     */
    public void setDateExpire(Object dateExpire) {
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
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The descriptionImage
     */
    public String getDescriptionImage() {
        return descriptionImage;
    }

    /**
     *
     * @param descriptionImage
     *     The description_image
     */
    public void setDescriptionImage(String descriptionImage) {
        this.descriptionImage = descriptionImage;
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


}
