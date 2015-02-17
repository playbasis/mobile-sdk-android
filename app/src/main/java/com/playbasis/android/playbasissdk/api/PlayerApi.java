package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Player;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class PlayerApi extends Api{
    public static final String TAG = "PlayerApi";
    
    
    public static void getPlayerInfo(@NonNull Playbasis playbasis, @NonNull String playerId, final OnResult<Player> listener){
        String uri = playbasis.getServerUrl() + "/Player/" + playerId;
        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Player player = JsonHelper.FromJsonObject(result.getJSONObject("player"), Player.class);
                    if(listener!=null)listener.onSuccess(player);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(listener!=null)listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if(listener!=null)listener.onError(error);
            }
        });
        
    }

    public static void getPlayerPrivateInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                      final OnResult<Player> listener){
        String uri = playbasis.getServerUrl() + "/Player/" + playerId;
        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Player player = JsonHelper.FromJsonObject(result.getJSONObject("player"), Player.class);
                    if(listener!=null)listener.onSuccess(player);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(listener!=null)listener.onError(new HttpError(e));
                }
            }
            @Override
            public void onError(HttpError error) {
                if(listener!=null)listener.onError(error);
            }
        });

    }
    
}
