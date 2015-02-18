package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/18/15.
 * For PlayBasisSdk project.
 */
public class Goods {
    public static final String TAG = "Goods";


    @Expose
    private String type;
    @SerializedName("goods_id")
    @Expose
    private String goodsId;
    @Expose
    private Integer quantity;
    @SerializedName("per_user")
    @Expose
    private Integer perUser;
    @Expose
    private String image;
    @SerializedName("sort_order")
    @Expose
    private Integer sortOrder;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private Redeem redeem;
    @Expose
    private String code;
    @Expose
    private Boolean sponsor;
    @SerializedName("date_start")
    @Expose
    private Object dateStart;
    @SerializedName("date_expire")
    @Expose
    private Object dateExpire;
    @SerializedName("is_group")
    @Expose
    private Boolean isGroup;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     *
     * @param goodsId
     * The goods_id
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     *
     * @return
     * The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     * The quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     * The perUser
     */
    public Integer getPerUser() {
        return perUser;
    }

    /**
     *
     * @param perUser
     * The per_user
     */
    public void setPerUser(Integer perUser) {
        this.perUser = perUser;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The sortOrder
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     *
     * @param sortOrder
     * The sort_order
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The redeem
     */
    public Redeem getRedeem() {
        return redeem;
    }

    /**
     *
     * @param redeem
     * The redeem
     */
    public void setRedeem(Redeem redeem) {
        this.redeem = redeem;
    }

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The sponsor
     */
    public Boolean getSponsor() {
        return sponsor;
    }

    /**
     *
     * @param sponsor
     * The sponsor
     */
    public void setSponsor(Boolean sponsor) {
        this.sponsor = sponsor;
    }

    /**
     *
     * @return
     * The dateStart
     */
    public Object getDateStart() {
        return dateStart;
    }

    /**
     *
     * @param dateStart
     * The date_start
     */
    public void setDateStart(Object dateStart) {
        this.dateStart = dateStart;
    }

    /**
     *
     * @return
     * The dateExpire
     */
    public Object getDateExpire() {
        return dateExpire;
    }

    /**
     *
     * @param dateExpire
     * The date_expire
     */
    public void setDateExpire(Object dateExpire) {
        this.dateExpire = dateExpire;
    }

    /**
     *
     * @return
     * The isGroup
     */
    public Boolean getIsGroup() {
        return isGroup;
    }

    /**
     *
     * @param isGroup
     * The is_group
     */
    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }
    
    private class Redeem {

        @Expose
        private Point point;
        @Expose
        private List<Badge> badge = new ArrayList<Badge>();

        /**
         *
         * @return
         * The point
         */
        public Point getPoint() {
            return point;
        }

        /**
         *
         * @param point
         * The point
         */
        public void setPoint(Point point) {
            this.point = point;
        }

        /**
         *
         * @return
         * The badge
         */
        public List<Badge> getBadge() {
            return badge;
        }

        /**
         *
         * @param badge
         * The badge
         */
        public void setBadge(List<Badge> badge) {
            this.badge = badge;
        }

    }


}
