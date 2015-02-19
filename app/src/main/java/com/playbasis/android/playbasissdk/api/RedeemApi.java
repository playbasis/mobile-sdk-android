package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.RedeemEvent;

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


    public static void goods(@NonNull Playbasis playbasis, Boolean isAsync,
                                       @NonNull String goodId, @NonNull String playerId, Integer amount,
                                       final OnResult<List<RedeemEvent>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._REDEEM_API + "/goods";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("goods_id", goodId));
        if(amount!=null)params.add(new BasicNameValuePair("amount", String.valueOf(amount)));

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

    public static void goodsGroup(@NonNull Playbasis playbasis, Boolean isAsync,
                                       @NonNull String groupId, @NonNull String playerId, Integer amount,
                                       final OnResult<List<RedeemEvent>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._REDEEM_API + "/goods";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("group", groupId));
        if(amount!=null)params.add(new BasicNameValuePair("amount", String.valueOf(amount)));

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
