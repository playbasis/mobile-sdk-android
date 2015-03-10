package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.helper.StringHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Goods;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/18/15.
 * For PlayBasisSdk project.
 */
public class GoodsApi extends Api {
    public static final String TAG = "GoodApi";

    /**
     * Returns information about all available goods for the current site.
     * @param playbasis Playbasis object.
     * @param listener Callback interface.
     */
    public static void listInfo(@NonNull Playbasis playbasis, final OnResult<List<Goods>> listener){
        String uri = playbasis.getUrl() + SDKUtil.GOODS_URL;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Goods> goodses = JsonHelper.FromJsonArray(result.getJSONArray("goods_list"), Goods.class);
                    if (listener != null) listener.onSuccess(goodses);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     * Returns information about the goods with the specified id.
     * @param playbasis Playbasis object.
     * @param goodsId Goods id.
     * @param listener Callback interface.
     */
    public static void info(@NonNull Playbasis playbasis, @NonNull String goodsId,
                             final OnResult<Goods> listener) {
        String uri = playbasis.getUrl() + SDKUtil._GOODS_URL + goodsId;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Goods goods = JsonHelper.FromJsonObject(result.getJSONObject("goods"), Goods.class);
                    if (listener != null) listener.onSuccess(goods);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     * Find number of available Goods given group.
     * @param playbasis Playbasis object.
     * @param playerId Player Id.
     * @param group Group Id.
     * @param amount Amount.
     * @param listener Callback interface.
     */
    public static void groupAvailable(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String group,
                            Integer amount, final OnResult<Integer> listener) {
        String uri = playbasis.getUrl() + SDKUtil.GOOD_GROUP_URL;
        
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("group", group));
        if(amount!=null) params.add(new BasicNameValuePair("amount", String.valueOf(amount)));

        StringGET(playbasis, uri, params, new OnResult<String>() {
            @Override
            public void onSuccess(String result) {
                    Integer response = Integer.valueOf(result);
                    if (listener != null) listener.onSuccess(response);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }
    
}
