package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick-Playbasis on 1/18/2016.
 */
public class NodeLeaderboard {

    @SerializedName("_id")
    @Expose
    String id;

    @Expose
    String name;

    public NodeLeaderboard() {
        this.id = "";
        this.name = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
