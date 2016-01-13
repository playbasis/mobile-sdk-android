package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TorIsHere on 1/6/2016 AD.
 */
public class Node {

    @SerializedName("_id")
    @Expose
    String id;

    @Expose
    String name;

    @Expose
    String description;

    @Expose
    boolean status;

    @Expose
    String slug;

    @SerializedName("date_added")
    @Expose
    String dateAdded;


    @SerializedName("date_modified")
    @Expose
    String dateModified;

    String parentId;
    String parentName;

    String organizeId;
    String organizeName;

    public Node() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.status = false;
        this.slug = "";
        this.dateAdded = "";
        this.dateModified = "";
        this.parentId = "";
        this.parentName = "";
        this.organizeId = "";
        this.organizeName = "";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

}
