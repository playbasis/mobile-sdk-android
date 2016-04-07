package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.RuleConfig;

/**
 * Created by TorIsHere on 1/28/2016 AD.
 */
// AKA REWARD
public class RuleGroup {

    @Expose
    String id;

    @Expose
    String name;

    @Expose
    String description;

    @SerializedName("specific_id")
    @Expose
    String specificId;

    @Expose
    String category;

    @SerializedName("sort_order")
    @Expose
    Integer sortOrder;

    @SerializedName("jigsaw_index")
    @Expose
    String jigsawIndex;

    @SerializedName("date_added")
    String dateAdded;

    @SerializedName("config")
    @Expose
    RuleConfig ruleConfig;

    @SerializedName("state")
    @Expose
    RuleState ruleState;

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

    public String getSpecificId() {
        return specificId;
    }

    public void setSpecificId(String specificId) {
        this.specificId = specificId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getJigsawIndex() {
        return jigsawIndex;
    }

    public void setJigsawIndex(String jigsawIndex) {
        this.jigsawIndex = jigsawIndex;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public RuleConfig getRuleConfig() {
        return ruleConfig;
    }

    public void setRuleConfig(RuleConfig ruleConfig) {
        this.ruleConfig = ruleConfig;
    }

    public RuleState getRuleState() {
        return ruleState;
    }

    public void setRuleState(RuleState ruleState) {
        this.ruleState = ruleState;
    }
}
