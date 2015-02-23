package com.playbasis.android.playbasissdk.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.helper.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 * This object is the request saved on the local storage 
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


    /**
     * return the current object into JSONObject.
     * * @return JsonObject of the model
     * @throws JSONException
     */
    public JSONObject toJson() throws JSONException {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return new JSONObject(json);
    }

    /**
     *  Return the current object into a json as string format
     * @return Json string
     */
    public String toJsonString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Map the jsonObject in the current object.
     * @param jsonObject Request jsonObject
     * @return request
     */
    public Request FromJson(JSONObject jsonObject){
        Gson gson = new Gson();
        Request request = gson.fromJson(jsonObject.toString(), Request.class);
        this.url = request.url;
        this.isAsync = request.isAsync;
        this.keyValuesBoddy = request.keyValuesBoddy;
        this.keyValuesHeader = request.keyValuesHeader;
        return this;
    }
}
