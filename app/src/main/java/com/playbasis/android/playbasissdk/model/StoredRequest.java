package com.playbasis.android.playbasissdk.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 * This object is the request saved on the local storage 
 */
public class StoredRequest {
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

    @Expose
    private Long timestamp;
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public StoredRequest withUrl(String url){
        this.url = url;
        return this;
    }

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean isAsync) {
        this.isAsync = isAsync;
    }
    
    public StoredRequest withAsync(boolean isAsync){
        this.isAsync = isAsync;
        return this;
    }

    public List<KeyValue> getKeyValuesBoddy() {
        return keyValuesBoddy;
    }

    public void setKeyValuesBoddy(List<KeyValue> keyValuesBoddy) {
        this.keyValuesBoddy = keyValuesBoddy;
    }
    
    public StoredRequest withKeyValueBody(List<KeyValue> keyValuesBoddy){
        this.keyValuesBoddy = keyValuesBoddy;
        return this;
    }

    public List<KeyValue> getKeyValuesHeader() {
        return keyValuesHeader;
    }

    public void setKeyValuesHeader(List<KeyValue> keyValuesHeader) {
        this.keyValuesHeader = keyValuesHeader;
    }
    
    public StoredRequest withKeyValueHeader(List<KeyValue> keyValuesHeader){
        this.keyValuesHeader = keyValuesHeader;
        return this;
        
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public StoredRequest withTimestamp(Long timestamp){
        this.timestamp = timestamp;
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
    public StoredRequest FromJson(JSONObject jsonObject){
        Gson gson = new Gson();
        StoredRequest storedRequest = gson.fromJson(jsonObject.toString(), StoredRequest.class);
        this.url = storedRequest.url;
        this.isAsync = storedRequest.isAsync;
        this.keyValuesBoddy = storedRequest.keyValuesBoddy;
        this.keyValuesHeader = storedRequest.keyValuesHeader;
        this.timestamp = storedRequest.timestamp;
        return this;
    }

    /**
     * Render keyValue params into json params 
     * @return json params
     */
    public JSONObject paramsToJson(){
        JSONObject json = new JSONObject();
        if(keyValuesBoddy!=null){
            for (KeyValue keyValue : keyValuesBoddy) {
                try {
                    json.put(keyValue.getKey(), keyValue.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }
}
