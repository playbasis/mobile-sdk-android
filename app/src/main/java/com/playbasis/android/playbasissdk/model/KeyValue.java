package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class KeyValue {
    public static final String TAG = "KeyValue";
    @Expose
    private String key;
    @Expose
    private String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public KeyValue withKey(String key) {
        this.key = key;
        return this;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public KeyValue withValue(String value){
        this.value = value;
        return this;
    }
}
