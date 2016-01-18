package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pongsakorn on 1/18/2016.
 */
public class Content {

    String detail;
    String date_start;
    String date_end;
    String image;
    String category;


    public Content() {
        this.detail = "";
        this.date_start = "";
        this.date_end = "";
        this.image = "";
        this.category = "";
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDateStart() {
        return date_start;
    }

    public void setDateStart(String dateStart) {
        this.date_start = dateStart;
    }

    public String getDateEnd() {
        return date_end;
    }

    public void setDateEnd(String dateEnd) {
        this.date_end = dateEnd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
