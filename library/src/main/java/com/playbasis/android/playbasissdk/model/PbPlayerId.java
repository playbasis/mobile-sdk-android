package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class PbPlayerId {
    public static final String TAG = "PbPlayerId";


    @Expose
    private String $id;

    /**
     *
     * @return
     * The $id
     */
    public String get$id() {
        return $id;
    }

    /**
     *
     * @param $id
     * The $id
     */
    public void set$id(String $id) {
        this.$id = $id;
    }

    @Override
    public String toString() {
        return "PbPlayerId{" +
                "$id='" + $id + '\'' +
                '}';
    }
}
