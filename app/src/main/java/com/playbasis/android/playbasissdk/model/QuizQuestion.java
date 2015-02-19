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
    private List<QuizQuestionOption> option;
    @Expose
    private Integer integer;
    @Expose
    private Integer total;
    @SerializedName("question_id")
    @Expose
    private String questionId;
}
