package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.RedeemEvent;
import com.playbasis.android.playbasissdk.model.RedeemGood;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class RedeemApi extends Api {
    public static final String TAG = "RedeemApi";

    /**
     * Redeem Goods for a client’s website.
     * @param playbasis Playbasis object.
     * @param goodId Goods id of goods store.
     * @param playerId Player id as used in client's website.
     * @param amount Amount of the goods to give to player.
     * @param listener Callback interface.
     */
    public static void goods(@NonNull Playbasis playbasis, 
                             @NonNull String goodId, @NonNull String playerId, Integer amount,
                             final OnResult<List<RedeemGood>>listener ){
        goods(playbasis,false,goodId,playerId,amount,listener);
    }

    /**
     * {@link #goods(com.playbasis.android.playbasissdk.core.Playbasis, String, String, Integer, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param goodId Goods id of goods store.
     * @param playerId Player id as used in client's website.
     * @param amount Amount of the goods to give to player.
     * @param listener Callback interface.
     */
    public static void goods(@NonNull Playbasis playbasis, boolean isAsync,
                                       @NonNull String goodId, @NonNull String playerId, Integer amount,
                                       final OnResult<List<RedeemGood>>listener ){
        String endpoint = SDKUtil._REDEEM_API + "goods";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("goods_id", goodId);
                if (amount != null) jsonObject.put("amount", String.valueOf(amount));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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
            params.add(new BasicNameValuePair("goods_id", goodId));
            if (amount != null) params.add(new BasicNameValuePair("amount", String.valueOf(amount)));

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        List<RedeemGood> events = JsonHelper.FromJsonArray(result.getJSONArray("events"), RedeemGood.class);
                        if (listener != null) listener.onSuccess(events);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) listener.onError(new HttpError(e));
                    }
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }
    }

    /**
     * Redeem Goods given group.
     * @param playbasis Playbasis object.
     * @param groupId Goods group.
     * @param playerId Player id as used in client's website.
     * @param amount Amount of the goods to give to player.
     * @param listener Callback interface.
     */
    public static void goodsGroup(@NonNull Playbasis playbasis, 
                                  @NonNull String groupId, @NonNull String playerId, Integer amount,
                                  final OnResult<List<RedeemEvent>>listener ){
        goodsGroup(playbasis,false,groupId,playerId,amount,listener);
    }
    /**
     * {@link #goodsGroup(com.playbasis.android.playbasissdk.core.Playbasis, String, String, Integer, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param groupId Goods group.
     * @param playerId Player id as used in client's website.
     * @param amount Amount of the goods to give to player.
     * @param listener Callback interface.
     */
    public static void goodsGroup(@NonNull Playbasis playbasis, boolean isAsync,
                                       @NonNull String groupId, @NonNull String playerId, Integer amount,
                                       final OnResult<List<RedeemEvent>>listener ){

        String endpoint = SDKUtil._REDEEM_API + "goodsGroup";
        
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("group", groupId);
                if (amount != null) jsonObject.put("amount", String.valueOf(amount));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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
            params.add(new BasicNameValuePair("group", groupId));
            if (amount != null) params.add(new BasicNameValuePair("amount", String.valueOf(amount)));

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                @SuppressWarnings("unchecked")
                public void onSuccess(JSONObject result) {
                    try {
                        List<RedeemEvent> events = JsonHelper.FromJsonArray(result.getJSONArray("events"), RedeemEvent.class);
                        if (listener != null) listener.onSuccess(events);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (listener != null) listener.onError(new HttpError(e));
                    }
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }
    }
}
