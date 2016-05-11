package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

import java.util.List;

/**
 * Created by TorIsHere on 2/19/2016 AD.
 */
public class PlayerOrganization {
    @SerializedName(ApiConst.NODE_ID)
    @Expose
    String id;

    @Expose
    String name;

    @SerializedName("organize_type")
    @Expose
    String organizeType;

    @Expose
    List<Role> roles;

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

    public String getOrganizeType() {
        return organizeType;
    }

    public void setOrganizeType(String organizeType) {
        this.organizeType = organizeType;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
