package com.playbasis.android.playbasissdk.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by TorIsHere on 1/7/2016 AD.
 */
public class Role {

    @SerializedName("role")
    @Expose
    String roleName;
    @SerializedName("join_date")
    @Expose
    String dateJoined;

    public Role() {
        this.roleName = "";
        this.dateJoined = "";
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }
}