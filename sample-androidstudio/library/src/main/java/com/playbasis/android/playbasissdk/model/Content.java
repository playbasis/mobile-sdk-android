package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pongsakorn on 1/18/2016.
 */
public class Content {

    @Expose
    String detail;
    @Expose
    String date_start;
    @Expose
    String date_end;
    @Expose
    String image;
    @Expose
    String category;
    @Expose
    String title;
    @Expose
    String summary;

    public Content() {
        this.detail = "";
        this.date_start = "";
        this.date_end = "";
        this.image = "";
        this.category = "";
        this.title = "";
        this.summary = "";
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
