package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class Request {
    public static final String TAG = "Request";

    @Expose
    private String url;
    @Expose
    private boolean isAsync;
    @SerializedName("body")
    @Expose
    private List<KeyValue> keyValuesBoddy;
    @SerializedName("header")
    @Expose
    private List<KeyValue> keyValuesHeader;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public Request withUrl(String url){
        this.url = url;
        return this;
    }

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean isAsync) {
        this.isAsync = isAsync;
    }
    
    public Request withAsync(boolean isAsync){
        this.isAsync = isAsync;
        return this;
    }

    public List<KeyValue> getKeyValuesBoddy() {
        return keyValuesBoddy;
    }

    public void setKeyValuesBoddy(List<KeyValue> keyValuesBoddy) {
        this.keyValuesBoddy = keyValuesBoddy;
    }
    
    public Request withKeyValueBody(List<KeyValue> keyValuesBoddy){
        this.keyValuesBoddy = keyValuesBoddy;
        return this;
    }

    public List<KeyValue> getKeyValuesHeader() {
        return keyValuesHeader;
    }

    public void setKeyValuesHeader(List<KeyValue> keyValuesHeader) {
        this.keyValuesHeader = keyValuesHeader;
    }
    
    public Request withKeyValueHeader(List<KeyValue> keyValuesHeader){
        this.keyValuesHeader = keyValuesHeader;
        return this;
        
    }
}
