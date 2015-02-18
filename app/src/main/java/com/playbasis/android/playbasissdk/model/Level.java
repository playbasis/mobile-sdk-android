package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/18/15.
 * For PlayBasisSdk project.
 */
public class Level {
    public static final String TAG = "Level";


    @SerializedName("level_title")
    @Expose
    private Object levelTitle;
    @Expose
    private Object level;
    @SerializedName("min_exp")
    @Expose
    private Object minExp;
    @SerializedName("max_exp")
    @Expose
    private Object maxExp;
    @SerializedName("level_image")
    @Expose
    private Object levelImage;

    /**
     *
     * @return
     * The levelTitle
     */
    public Object getLevelTitle() {
        return levelTitle;
    }

    /**
     *
     * @param levelTitle
     * The level_title
     */
    public void setLevelTitle(Object levelTitle) {
        this.levelTitle = levelTitle;
    }

    public Level withLevelTitle(Object levelTitle) {
        this.levelTitle = levelTitle;
        return this;
    }

    /**
     *
     * @return
     * The level
     */
    public Object getLevel() {
        return level;
    }

    /**
     *
     * @param level
     * The level
     */
    public void setLevel(Object level) {
        this.level = level;
    }

    public Level withLevel(Object level) {
        this.level = level;
        return this;
    }

    /**
     *
     * @return
     * The minExp
     */
    public Object getMinExp() {
        return minExp;
    }

    /**
     *
     * @param minExp
     * The min_exp
     */
    public void setMinExp(Object minExp) {
        this.minExp = minExp;
    }

    public Level withMinExp(Object minExp) {
        this.minExp = minExp;
        return this;
    }

    /**
     *
     * @return
     * The maxExp
     */
    public Object getMaxExp() {
        return maxExp;
    }

    /**
     *
     * @param maxExp
     * The max_exp
     */
    public void setMaxExp(Object maxExp) {
        this.maxExp = maxExp;
    }

    public Level withMaxExp(Object maxExp) {
        this.maxExp = maxExp;
        return this;
    }

    /**
     *
     * @return
     * The levelImage
     */
    public Object getLevelImage() {
        return levelImage;
    }

    /**
     *
     * @param levelImage
     * The level_image
     */
    public void setLevelImage(Object levelImage) {
        this.levelImage = levelImage;
    }

    public Level withLevelImage(Object levelImage) {
        this.levelImage = levelImage;
        return this;
    }
}
