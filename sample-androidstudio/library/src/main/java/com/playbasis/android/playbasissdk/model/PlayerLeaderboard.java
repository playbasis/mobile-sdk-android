package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nick-Playbasis on 1/13/2016.
 */
public class PlayerLeaderboard {
    @SerializedName("cl_player_id")
    @Expose
    String playerId;

    @Expose
    String image;

    @Expose
    String username;

    @SerializedName("first_name")
    @Expose
    String firstName;

    @SerializedName("last_name")
    @Expose
    String lastName;


    public PlayerLeaderboard() {
        this.playerId = "";
        this.image = "";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String img) {
        this.image = img;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first) {
        this.firstName = first;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last) {
        this.lastName = last;
    }



}
