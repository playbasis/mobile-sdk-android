package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class ActionConfig {
    public static final String TAG = "ActionConfig";

    @Expose
    private String name;
    @Expose
    private String key;
    @Expose
    private List<Config> config = new ArrayList<Config>();

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
     * The config
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     *
     * @param config
     * The config
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
