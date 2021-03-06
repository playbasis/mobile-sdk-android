package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.helper.StringHelper;

import java.util.List;
/**
 * Created by ToR on 10/18/15.
 */
public class Condition {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String category;
    @SerializedName("sort_order")
    @Expose
    private int sortOrder;

    /**
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return sortOrder
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     *
     * @param sortOrder
     */
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
