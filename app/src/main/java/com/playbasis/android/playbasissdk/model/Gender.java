package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/17/15.
 * For PlayBasisSdk project.
 */
public enum Gender {
    @SerializedName("1")
    MALE(1),
    @SerializedName("2")
    FEMALE(2);

    private int gender;

    private Gender(int g) {
        gender = g;
    }

    public int getGender() {
        return gender;
    }
}
