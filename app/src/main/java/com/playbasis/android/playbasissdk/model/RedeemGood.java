package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/24/15.
 * For PlayBasisSdk project.
 */
public class RedeemGood {
    public static final String TAG = "RedeemGood";


    @SerializedName("event_type")
    @Expose
    private  String eventType;
    
    @Expose
    private Integer value;
    
    @SerializedName("goods_data")
    @Expose
    private Goods goodsData;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Goods getGoodsData() {
        return goodsData;
    }

    public void setGoodsData(Goods goodsData) {
        this.goodsData = goodsData;
    }
}
