package com.playbasis.android.playbasissdk.secure;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.model.KeyValue;
import com.playbasis.android.playbasissdk.model.Request;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class RequestStorage {
    public static final String TAG = "RequestStorage";
    
    public void saveRequest(Playbasis playbasis, String url, List<NameValuePair> param){
        Request request = new Request()
                .withUrl(url)
                .withAsync(false)
                .withKeyValueBody(keyValueParams(playbasis, param))
                .withKeyValueHeader(null);
        
        
    }

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
    
    private void encryptFile(){
        
        
    }
}
