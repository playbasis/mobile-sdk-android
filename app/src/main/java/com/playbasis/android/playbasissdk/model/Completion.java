
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Completion {

    @SerializedName("completion_filter")
    @Expose
    private String completionFilter;
    @SerializedName("completion_value")
    @Expose
    private String completionValue;
    @SerializedName("completion_id")
    @Expose
    private String completionId;
    @SerializedName("completion_element_id")
    @Expose
    private String completionElementId;
    @SerializedName("completion_type")
    @Expose
    private String completionType;
    @SerializedName("completion_title")
    @Expose
    private String completionTitle;
    @SerializedName("completion_data")
    @Expose
    private CompletionData completionData;

    /**
     * 
     * @return
     *     The completionFilter
     */
    public String getCompletionFilter() {
        return completionFilter;
    }

    /**
     * 
     * @param completionFilter
     *     The completion_filter
     */
    public void setCompletionFilter(String completionFilter) {
        this.completionFilter = completionFilter;
    }

    public Completion withCompletionFilter(String completionFilter) {
        this.completionFilter = completionFilter;
        return this;
    }

    /**
     * 
     * @return
     *     The completionValue
     */
    public String getCompletionValue() {
        return completionValue;
    }

    /**
     * 
     * @param completionValue
     *     The completion_value
     */
    public void setCompletionValue(String completionValue) {
        this.completionValue = completionValue;
    }

    public Completion withCompletionValue(String completionValue) {
        this.completionValue = completionValue;
        return this;
    }

    /**
     * 
     * @return
     *     The completionId
     */
    public String getCompletionId() {
        return completionId;
    }

    /**
     * 
     * @param completionId
     *     The completion_id
     */
    public void setCompletionId(String completionId) {
        this.completionId = completionId;
    }

    public Completion withCompletionId(String completionId) {
        this.completionId = completionId;
        return this;
    }

    /**
     * 
     * @return
     *     The completionElementId
     */
    public String getCompletionElementId() {
        return completionElementId;
    }

    /**
     * 
     * @param completionElementId
     *     The completion_element_id
     */
    public void setCompletionElementId(String completionElementId) {
        this.completionElementId = completionElementId;
    }

    public Completion withCompletionElementId(String completionElementId) {
        this.completionElementId = completionElementId;
        return this;
    }

    /**
     * 
     * @return
     *     The completionType
     */
    public String getCompletionType() {
        return completionType;
    }

    /**
     * 
     * @param completionType
     *     The completion_type
     */
    public void setCompletionType(String completionType) {
        this.completionType = completionType;
    }

    public Completion withCompletionType(String completionType) {
        this.completionType = completionType;
        return this;
    }

    /**
     * 
     * @return
     *     The completionTitle
     */
    public String getCompletionTitle() {
        return completionTitle;
    }

    /**
     * 
     * @param completionTitle
     *     The completion_title
     */
    public void setCompletionTitle(String completionTitle) {
        this.completionTitle = completionTitle;
    }

    public Completion withCompletionTitle(String completionTitle) {
        this.completionTitle = completionTitle;
        return this;
    }

    /**
     * 
     * @return
     *     The completionData
     */
    public CompletionData getCompletionData() {
        return completionData;
    }

    /**
     * 
     * @param completionData
     *     The completion_data
     */
    public void setCompletionData(CompletionData completionData) {
        this.completionData = completionData;
    }

    public Completion withCompletionData(CompletionData completionData) {
        this.completionData = completionData;
        return this;
    }

}
