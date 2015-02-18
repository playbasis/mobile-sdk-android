
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Badge {

    @SerializedName("badge_id")
    @Expose
    private String badgeId;
    @Expose
    private Integer redeemed;
    @Expose
    private Integer claimed;
    @Expose
    private String image;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private Integer amount;
    @Expose
    private String hint;

    /**
     * 
     * @return
     *     The badgeId
     */
    public String getBadgeId() {
        return badgeId;
    }

    /**
     * 
     * @param badgeId
     *     The badge_id
     */
    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    /**
     * 
     * @return
     *     The redeemed
     */
    public Integer getRedeemed() {
        return redeemed;
    }

    /**
     * 
     * @param redeemed
     *     The redeemed
     */
    public void setRedeemed(Integer redeemed) {
        this.redeemed = redeemed;
    }

    /**
     * 
     * @return
     *     The claimed
     */
    public Integer getClaimed() {
        return claimed;
    }

    /**
     * 
     * @param claimed
     *     The claimed
     */
    public void setClaimed(Integer claimed) {
        this.claimed = claimed;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
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

    /**
     * 
     * @return
     *     The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * 
     * @param hint
     *     The hint
     */
    public void setHint(String hint) {
        this.hint = hint;
    }


    @Override
    public String toString() {
        return "Badge{" +
                "badgeId='" + badgeId + '\'' +
                ", redeemed=" + redeemed +
                ", claimed=" + claimed +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", hint='" + hint + '\'' +
                '}';
    }
}