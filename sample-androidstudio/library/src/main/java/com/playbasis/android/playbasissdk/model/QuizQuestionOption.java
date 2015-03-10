package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class QuizQuestionOption {
    public static final String TAG = "QuizQuestionOption";

    @Expose
    private String option;
    @SerializedName("option_image")
    @Expose
    private String optionImage;
    @SerializedName("option_id")
    @Expose
    private String OptionId;

    /**
     *  
     * @return question option
     */
    public String getOption() {
        return option;
    }

    /**
     *  
     * @param option question option
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     *  
     * @return question option image url
     */
    public String getOptionImage() {
        return optionImage;
    }

    /**
     *  
     * @param optionImage question option image url
     */
    public void setOptionImage(String optionImage) {
        this.optionImage = optionImage;
    }

    /**
     *  
     * @return option Id
     */
    public String getOptionId() {
        return OptionId;
    }

    /**
     *  
     * @param optionId option Id
     */
    public void setOptionId(String optionId) {
        OptionId = optionId;
    }

    @Override
    public String toString() {
        return "QuizQuestionOption{" +
                "option='" + option + '\'' +
                ", optionImage='" + optionImage + '\'' +
                ", OptionId='" + OptionId + '\'' +
                '}';
    }
}
