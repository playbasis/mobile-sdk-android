package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public abstract class AbstractGrade {
    public static final String TAG = "Grade";


    @SerializedName("grade_id")
    @Expose
    private String gradeId;
    @Expose
    private String start;
    @Expose
    private String end;
    @Expose
    private String grade;
    @Expose
    private String rank;
    @SerializedName("rank_image")
    @Expose
    private String rankImage;
    @Expose
    private Integer score;
    @SerializedName("max_score")
    @Expose
    private String maxScore;
    @SerializedName("total_score")
    @Expose
    private Integer totalScore;
    @SerializedName("total_max_score")
    @Expose
    private Integer totalMaxScore;

    /**
     *
     * @return
     *     The gradeId
     */
    public String getGradeId() {
        return gradeId;
    }

    /**
     *
     * @param gradeId
     *     The grade_id
     */
    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    /**
     *
     * @return
     *     The start
     */
    public String getStart() {
        return start;
    }

    /**
     *
     * @param start
     *     The start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     *
     * @return
     *     The end
     */
    public String getEnd() {
        return end;
    }

    /**
     *
     * @param end
     *     The end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     *
     * @return
     *     The grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     *
     * @param grade
     *     The grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     *
     * @return
     *     The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     *     The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     *     The rankImage
     */
    public String getRankImage() {
        return rankImage;
    }

    /**
     *
     * @param rankImage
     *     The rank_image
     */
    public void setRankImage(String rankImage) {
        this.rankImage = rankImage;
    }

    /**
     *
     * @return
     *     The score
     */
    public Integer getScore() {
        return score;
    }

    /**
     *
     * @param score
     *     The score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     *
     * @return
     *     The maxScore
     */
    public String getMaxScore() {
        return maxScore;
    }

    /**
     *
     * @param maxScore
     *     The max_score
     */
    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
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


}
