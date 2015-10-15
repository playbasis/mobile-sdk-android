package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.RequestError;
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


    /**
     * Returns names of actions that can trigger game rules within a client’s website.
     * @param playbasis Playbasis object.
     * @param listener Callback interface.
     */
    public static void actionConfig(@NonNull Playbasis playbasis, final OnResult<List<ActionConfig>> listener) {
        String uri = playbasis.getUrl() + SDKUtil._ENGINE_URL + "actionConfig";

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<ActionConfig> actionConfigs = ActionConfig.parseEngineRules(result);
                    if (listener != null) listener.onSuccess(actionConfigs);
                } catch (JSONException e) {
                    listener.onError(new HttpError(e));
                }
                
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     *  Process an action through all the game rules defined for a client’s website.
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param action Name of action performed.
     * @param playerId Player id as used in client's website.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *                        URL specific rules, and rules that trigger only when a specific identifier string is
     *                        supplied.
     * @param reward Name of the point-based reward to give to player, if the action trigger custom-point reward
     *                             that doesn't specify reward name.
     * @param quantity Amount of the point-based reward to give to player, if the action trigger custom-point reward
     *                                  that doesn't specify reward quantity.
     * @param listener Callback interface.
     */
    public static void rule(@NonNull final Playbasis playbasis, final boolean isAsync,
                            @NonNull final String action, @NonNull final String playerId, final String url, final String reward,
                            final String quantity,  final OnResult<Rule> listener){

        if (playbasis.isNetworkAvailable()) {
            PlayerValidatorApi.playerValidation(playbasis, playerId, new OnResult<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        NTPdate.GetNTPDate(playbasis, new NTPdate.OnDate() {
                            @Override
                            public void onDate(Long date) {
                                ruleRequest(playbasis, isAsync, action, playerId, url, date, reward, quantity, 
                                        listener);
                            }

                            @Override
                            public void onError(RequestError error) {
                                if (listener != null) listener.onError(new HttpError(error));
                            }
                        });
                        
                    } else {
                        if (listener != null) listener.onError(new HttpError(new RequestError("update player fail",
                                RequestError.ERROR_CODE.DEFAULT)));
                    }

                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }else{
            ruleRequest(playbasis, isAsync, action, playerId, url, NTPdate.GetLocalDate(playbasis), reward, quantity,
                    listener);
        }
    }

    private static void ruleRequest(@NonNull Playbasis playbasis, boolean isAsync,
                            @NonNull String action, @NonNull String playerId, String url, Long dateTime, String reward,
                            String quantity,  final OnResult<Rule> listener){

        String endpoint = SDKUtil._ENGINE_URL + "rule";
        System.out.println("Rule request");
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

            asyncPost(playbasis, endpoint, dateTime, jsonObject, new OnResult<String>() {
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

            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            params.add(new BasicNameValuePair("action", action));
            if (url != null) params.add(new BasicNameValuePair("url", url));
            if (reward != null) params.add(new BasicNameValuePair("reward", reward));
            if (quantity != null) params.add(new BasicNameValuePair("quantity", quantity));

            JsonObjectPOST(playbasis, uri, dateTime, params, new OnResult<JSONObject>() {
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

    /**
     *
     *  @param playbasis Playbasus object
     *  @param ruleId    rule ID
     *
     *  @param listener Callback interface.
     */
    public static void ruleDetail(@NonNull final Playbasis playbasis, final String ruleId, final OnResult<RuleDetail> listener) {

    }
}
