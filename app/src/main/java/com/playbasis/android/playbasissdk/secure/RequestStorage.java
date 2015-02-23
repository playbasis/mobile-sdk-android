package com.playbasis.android.playbasissdk.secure;

import android.content.Context;
import android.util.Log;

import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.google.gson.JsonArray;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.helper.StringHelper;
import com.playbasis.android.playbasissdk.model.KeyValue;
import com.playbasis.android.playbasissdk.model.Request;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        Request request = new Request()
                .withUrl(url)
                .withAsync(false)
                .withKeyValueBody(keyValueParams(playbasis, param))
                .withKeyValueHeader(null);

        return write(request);
        
    }

    public Boolean save(Playbasis playbasis, String url, JSONObject params){
        Request request = new Request()
                .withUrl(url)
                .withAsync(false)
                .withKeyValueBody(keyValueParams(params))
                .withKeyValueHeader(null);

        return write(request);

    }

    /**
     * Read all saved request form cache and clear the cache. 
     * @return list saved request.
     */
    public List<Request> LoadAll(){
        List<Request> requests = readAll();
        clearCache();
        return requests;
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
        params.add(new KeyValue("api_key",playbasis.getKeyStore().getApiKey() ));
        params.add(new KeyValue("token",playbasis.getAuthenticator().getToken() ));
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
     * @param request Request to add.
     * @return success.
     */
    private Boolean write(Request request){
        List<Request> requests = readAll();
        requests.add(request);
        return write(requests);
    }

    /**
     * Write list Request to the local storage. 
     * @param requests request list.
     * @return success.
     */
    private Boolean write(List<Request> requests){
        String content = "[";
        if(requests!=null){
            for (Request request :requests){
                if(request!=null){
                    content+= request.toJsonString();
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
        return new JSONArray(read());
    }

    /**
     * Read al request saved into the cache. 
     * @return Request list
     */
    private List<Request> readAll(){
        List<Request> requests = new ArrayList<>();

        try {
            requests = JsonHelper.FromJsonArray(readJSONArray(), Request.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
}
