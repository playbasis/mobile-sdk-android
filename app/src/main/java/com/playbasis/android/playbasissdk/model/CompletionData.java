
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompletionData {

    @SerializedName("action_id")
    @Expose
    private String actionId;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String icon;
    @Expose
    private String color;

    /**
     * 
     * @return
     *     The actionId
     */
    public String getActionId() {
        return actionId;
    }

    /**
     * 
     * @param actionId
     *     The action_id
     */
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public CompletionData withActionId(String actionId) {
        this.actionId = actionId;
        return this;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public CompletionData withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public CompletionData withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public CompletionData withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    /**
     * 
     * @return
     *     The color
     */
    public String getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    public void setColor(String color) {
        this.color = color;
    }

    public CompletionData withColor(String color) {
        this.color = color;
        return this;
    }


    @Override
    public String toString() {
        return "CompletionData{" +
                "actionId='" + actionId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
