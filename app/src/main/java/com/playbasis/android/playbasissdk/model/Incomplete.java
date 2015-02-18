
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Incomplete {

    @SerializedName("incompletion_id")
    @Expose
    private String incompletionId;
    @SerializedName("incompletion_type")
    @Expose
    private String incompletionType;
    @SerializedName("incompletion_value")
    @Expose
    private Integer incompletionValue;
    @SerializedName("incompletion_element_id")
    @Expose
    private String incompletionElementId;
    @SerializedName("incompletion_filter")
    @Expose
    private String incompletionFilter;

    /**
     * 
     * @return
     *     The incompletionId
     */
    public String getIncompletionId() {
        return incompletionId;
    }

    /**
     * 
     * @param incompletionId
     *     The incompletion_id
     */
    public void setIncompletionId(String incompletionId) {
        this.incompletionId = incompletionId;
    }

    public Incomplete withIncompletionId(String incompletionId) {
        this.incompletionId = incompletionId;
        return this;
    }

    /**
     * 
     * @return
     *     The incompletionType
     */
    public String getIncompletionType() {
        return incompletionType;
    }

    /**
     * 
     * @param incompletionType
     *     The incompletion_type
     */
    public void setIncompletionType(String incompletionType) {
        this.incompletionType = incompletionType;
    }

    public Incomplete withIncompletionType(String incompletionType) {
        this.incompletionType = incompletionType;
        return this;
    }

    /**
     * 
     * @return
     *     The incompletionValue
     */
    public Integer getIncompletionValue() {
        return incompletionValue;
    }

    /**
     * 
     * @param incompletionValue
     *     The incompletion_value
     */
    public void setIncompletionValue(Integer incompletionValue) {
        this.incompletionValue = incompletionValue;
    }

    public Incomplete withIncompletionValue(Integer incompletionValue) {
        this.incompletionValue = incompletionValue;
        return this;
    }

    /**
     * 
     * @return
     *     The incompletionElementId
     */
    public String getIncompletionElementId() {
        return incompletionElementId;
    }

    /**
     * 
     * @param incompletionElementId
     *     The incompletion_element_id
     */
    public void setIncompletionElementId(String incompletionElementId) {
        this.incompletionElementId = incompletionElementId;
    }

    public Incomplete withIncompletionElementId(String incompletionElementId) {
        this.incompletionElementId = incompletionElementId;
        return this;
    }

    /**
     * 
     * @return
     *     The incompletionFilter
     */
    public String getIncompletionFilter() {
        return incompletionFilter;
    }

    /**
     * 
     * @param incompletionFilter
     *     The incompletion_filter
     */
    public void setIncompletionFilter(String incompletionFilter) {
        this.incompletionFilter = incompletionFilter;
    }

    public Incomplete withIncompletionFilter(String incompletionFilter) {
        this.incompletionFilter = incompletionFilter;
        return this;
    }

}
