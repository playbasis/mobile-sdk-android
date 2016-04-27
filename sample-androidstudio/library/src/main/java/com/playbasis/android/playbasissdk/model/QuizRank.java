package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class QuizRank {
    public static final String TAG = "QuizRank";
    
    @Expose
    @SerializedName("pb_player_id")
    private String pbPlayerId;
    @Expose
    @SerializedName(ApiConst.PLAYER_ID)
    private String playerId;
    @Expose
    private Integer score; 
}
