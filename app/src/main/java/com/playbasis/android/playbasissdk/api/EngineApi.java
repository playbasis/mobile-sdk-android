package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Rule;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class EngineApi extends Api {
    public static final String TAG = "EngineApi";


    public static void actionConfig(@NonNull Playbasis playbasis, final OnResult<ActionConfig> listener) {
        String uri = SDKUtil.SERVER_URL + SDKUtil._ENGINE_URL + "actionConfig";

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ActionConfig actionConfig = JsonHelper.FromJsonObject(result, ActionConfig.class);
                if (listener != null) listener.onSuccess(actionConfig);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }
    
    public static void rule(@NonNull Playbasis playbasis, boolean isAsync, 
                            @NonNull String action, @NonNull String playerId, String url, String reward, 
                            String quantity,  final OnResult<Rule> listener){

        String endpoint = SDKUtil._ENGINE_URL + "rule";
        
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("action", action);
               if(url!=null) jsonObject.put("url", url);
               if(reward!=null) jsonObject.put("reward", reward);
               if(quantity!=null) jsonObject.put("quantity", quantity);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {

            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            params.add(new BasicNameValuePair("action", action));
            if (url != null) params.add(new BasicNameValuePair("url", url));
            if (reward != null) params.add(new BasicNameValuePair("reward", reward));
            if (quantity != null) params.add(new BasicNameValuePair("quantity", quantity));

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    Rule rule = JsonHelper.FromJsonObject(result, Rule.class);
                    if (listener != null) listener.onSuccess(rule);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });

        }
    }

}
