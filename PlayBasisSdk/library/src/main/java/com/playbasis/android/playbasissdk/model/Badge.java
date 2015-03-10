
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Badge extends BadgeData {


    @Expose
    private Integer redeemed;
    @Expose
    private Integer claimed;

    @Expose
    private Integer amount;

    @Expose
    @SerializedName("sort_order")
    private Integer shortOrder;
    

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
     * @return The ShortOrder
     */
    public Integer getShortOrder() {
        return shortOrder;
    }

    /**
     *  
     * @param shortOrder The ShortOrder
     */
    public void setShortOrder(Integer shortOrder) {
        this.shortOrder = shortOrder;
    }


    @Override
    public String toString() {
        return "Badge{" +
                "redeemed=" + redeemed +
                ", claimed=" + claimed +
                ", amount=" + amount +
                ", shortOrder=" + shortOrder +
                '}';
    }
}
