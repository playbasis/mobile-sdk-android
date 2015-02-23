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

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOptionImage() {
        return optionImage;
    }

    public void setOptionImage(String optionImage) {
        this.optionImage = optionImage;
    }

    public String getOptionId() {
        return OptionId;
    }

    public void setOptionId(String optionId) {
        OptionId = optionId;
    }
}
