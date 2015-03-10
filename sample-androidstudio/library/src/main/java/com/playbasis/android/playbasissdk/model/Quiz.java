
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

    @Expose
    private String name;
    
    @Expose
    private String weight;
    
    @Expose
    private String description;
    
    @SerializedName("description_image")
    @Expose
    private String descriptionImage;

    @Expose
    private String image;
    
    
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

    /**
     *  
     * @return The quiz name
     */
    public String getName() {
        return name;
    }

    /**
     *  
     * @param name The quiz name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  
     * @return The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *  
     * @param weight The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     *  
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *  
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  
     * @return The image description
     */
    public String getDescriptionImage() {
        return descriptionImage;
    }

    /**
     *  
     * @param descriptionImage The image description
     */
    public void setDescriptionImage(String descriptionImage) {
        this.descriptionImage = descriptionImage;
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


    @Override
    public String toString() {
        return "Quiz{" +
                "quizId='" + quizId + '\'' +
                ", grade=" + grade +
                ", value=" + value +
                ", totalCompletedQuestions=" + totalCompletedQuestions +
                ", name='" + name + '\'' +
                ", weight='" + weight + '\'' +
                ", description='" + description + '\'' +
                ", descriptionImage='" + descriptionImage + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
