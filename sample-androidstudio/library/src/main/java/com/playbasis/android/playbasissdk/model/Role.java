package com.playbasis.android.playbasissdk.model;

/**
 * Created by TorIsHere on 1/7/2016 AD.
 */
public class Role {

    String roleName;
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
