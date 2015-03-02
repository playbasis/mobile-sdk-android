
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RewardData {

    @SerializedName("badge_id")
    @Expose
    private String badgeId;
    @Expose
    private String image;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String hint;
    @Expose
    private Boolean sponsor;
    @Expose
    private Boolean claim;
    @Expose
    private Boolean redeem;

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

    public RewardData withBadgeId(String badgeId) {
        this.badgeId = badgeId;
        return this;
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

    public RewardData withImage(String image) {
        this.image = image;
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

    public RewardData withName(String name) {
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

    public RewardData withDescription(String description) {
        this.description = description;
        return this;
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

    public RewardData withHint(String hint) {
        this.hint = hint;
        return this;
    }

    /**
     *
     * @return
     *     The sponsor
     */
    public Boolean getSponsor() {
        return sponsor;
    }

    /**
     *
     * @param sponsor
     *     The sponsor
     */
    public void setSponsor(Boolean sponsor) {
        this.sponsor = sponsor;
    }

    public RewardData withSponsor(Boolean sponsor) {
        this.sponsor = sponsor;
        return this;
    }

    /**
     *
     * @return
     *     The claim
     */
    public Boolean getClaim() {
        return claim;
    }

    /**
     *
     * @param claim
     *     The claim
     */
    public void setClaim(Boolean claim) {
        this.claim = claim;
    }

    public RewardData withClaim(Boolean claim) {
        this.claim = claim;
        return this;
    }

    /**
     *
     * @return
     *     The redeem
     */
    public Boolean getRedeem() {
        return redeem;
    }

    /**
     *
     * @param redeem
     *     The redeem
     */
    public void setRedeem(Boolean redeem) {
        this.redeem = redeem;
    }

    public RewardData withRedeem(Boolean redeem) {
        this.redeem = redeem;
        return this;
    }

}
