package com.playbasis.android.playbasissdk.secure;

import android.content.Context;
import android.util.Log;

import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.DateHelper;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.helper.StringHelper;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.model.KeyValue;
import com.playbasis.android.playbasissdk.model.StoredRequest;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 * Save and load requests into the encrypt local storage.
 */
public class RequestStorage {
    public static final String TAG = "RequestStorage";

    private Context mContext;
    private File mFile;
    private Entity mEntity;

    public RequestStorage(Context context) {
        this.mContext = context;
        mFile = new File(mContext.getFilesDir(), "requestCache");
        mEntity = new Entity("requestCache");
    }

    /**
     * Save the sync request into the local storage. 
     * @param playbasis Playbasis object.
     * @param url Url of the request.
     * @param param Param of the request.
     * @return save success.              
     */
    public Boolean save(Playbasis playbasis, String url, List<NameValuePair> param){
        StoredRequest storedRequest = new StoredRequest()
                .withUrl(StringHelper.removeFirstOccurrence(playbasis.getUrl(), url))
                .withAsync(false)
                .withKeyValueBody(keyValueParams(playbasis, param))
                .withTimestamp(DateHelper.currentTimetamp())
                .withKeyValueHeader(null);

        return write(storedRequest);
        
    }
    /**
     * Save the sync request into the local storage.
     * @param playbasis Playbasis object.
     * @param url Url of the request.
     * @param params Json Param of the request.
     * @return save success.
     */
    public Boolean save(Playbasis playbasis, String url, JSONObject params, Long timestamp){
        StoredRequest storedRequest = new StoredRequest()
                .withUrl(url)
                .withAsync(false)
                .withKeyValueBody(keyValueParams(params))
                .withKeyValueHeader(null)
                .withTimestamp(timestamp!=null? timestamp : DateHelper.currentTimetamp());

        return write(storedRequest);

    }

    /**
     * Read all saved request form cache and clear the cache. 
     * @return list saved request.
     */
    public List<StoredRequest> loadAll(){
        List<StoredRequest> storedRequests = readAll();
        clearCache();
        return storedRequests;
    }

    /**
     * Clear the request cache. 
     */
    public void clearCache(){
        write("");
    }

    /**
     * Add the api_key and token to the request params 
     * @param playbasis The playbasis object.
     * @param httpParams Request http params
     * @return new params
     */
    private List<KeyValue> keyValueParams(Playbasis playbasis, List<NameValuePair> httpParams){
        List<KeyValue> params = new ArrayList<>();
        if(httpParams!=null){
            for (NameValuePair pair : httpParams){
                params.add(new KeyValue(pair.getName(), pair.getValue()));
            }
        }
        return params;
    }

    /**
     * Set json params into List KeyValue.
     * @param jsonObject json params.
     * @return list KeyValue.
     */
    private List<KeyValue> keyValueParams(JSONObject jsonObject){
        Iterator<String> keys = jsonObject.keys();
        List<KeyValue> params = new ArrayList<>();
        while(keys.hasNext()){
            String key = keys.next();
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(key);
            try {
                keyValue.setValue(String.valueOf(jsonObject.get(key)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params.add(keyValue);
        }
        return params;

    }
    
    /**
     * Add the request to the cache storage. 
     * @param storedRequest Request to add.
     * @return success.
     */
    private Boolean write(StoredRequest storedRequest){
        List<StoredRequest> storedRequests = readAll();
        storedRequests.add(storedRequest);
        return write(storedRequests);
    }

    /**
     * Write list Request to the local storage. 
     * @param storedRequests request list.
     * @return success.
     */
    private Boolean write(List<StoredRequest> storedRequests){
        String content = "[";
        if(storedRequests !=null){
            for (StoredRequest storedRequest : storedRequests){
                if(storedRequest !=null){
                    content+= storedRequest.toJsonString();
                    content+=",";
                }
            }
        }
        StringHelper.removeLastChar(content);
        content+="]";
        return write(content);
    }

    /**
     * Write string into the encryptFile. Override previous data.
     * @param content String to write.
     * @return write success.                
     */
    private Boolean write(String content){
        if(content==null){
            Log.e("CONTREAL", "Write::String null");
            return false;
        }
        try {
            return ConcealHelper.encryptFile(mContext, mFile, mEntity, content.getBytes());
        } catch (IOException e) {
            Log.e("CONTREAL", "Write::IOException: " + e.toString());
            return false;
        } catch (KeyChainException e) {
            Log.e("CONTREAL", "Write::KeyChainException: " + e.toString());
            return false;
        } catch (CryptoInitializationException e) {
            Log.e("CONTREAL", "Write::CryptoInitializationException: " + e.toString());
            return false;
        }

    }

    /**
     * Read the string encrypt file.
     * @return String file content.
     */
    private String read(){
        String content = "";
        try {
            content = ConcealHelper.decryptFile(mContext, mFile, mEntity);
        } catch (IOException e) {
            Log.e("CONTREAL", "Read::IOException: " + e.toString());
        } catch (KeyChainException e) {
            Log.e("CONTREAL", "Read::KeyChainException: " + e.toString());
        } catch (CryptoInitializationException e) {
            Log.e("CONTREAL", "Read::CryptoInitializationException: " + e.toString());
        }
        return content;
    }

    /**
     * Read the cache into jsonArray 
     * @return jsonArray
     * @throws JSONException
     */
    private JSONArray readJSONArray() throws JSONException {
        String read = read();
        if(Validator.isValid(read))
            return new JSONArray(read);
        else
            return new JSONArray();
    }

    /**
     * Read all requests saved into the cache.
     * @return Request list
     */
    private List<StoredRequest> readAll(){
        List<StoredRequest> storedRequests = new ArrayList<>();

        try {
            storedRequests = JsonHelper.FromJsonArray(readJSONArray(), StoredRequest.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return storedRequests;
    }
    
}
