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
}
