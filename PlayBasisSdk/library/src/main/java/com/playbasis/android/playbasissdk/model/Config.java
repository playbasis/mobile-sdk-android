package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Config {
    public static final String TAG = "Config";

    @Expose
    private String url;

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Config{" +
                "url='" + url + '\'' +
                '}';
    }
}
