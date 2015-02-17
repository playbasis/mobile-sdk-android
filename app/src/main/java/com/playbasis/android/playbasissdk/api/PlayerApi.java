package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.helper.StringHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.PlaybasisResponse;
import com.playbasis.android.playbasissdk.http.RequestError;
import com.playbasis.android.playbasissdk.model.Player;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class PlayerApi extends Api{
    public static final String TAG = "PlayerApi";
    
    
    private static void getPlayer(@NonNull Playbasis playbasis, String uri, final OnResult<Player> listener) {
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

    private static void getPlayerPrivate(@NonNull Playbasis playbasis, @NonNull String uri,
                                            final OnResult<Player> listener){
        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Player player = JsonHelper.FromJsonObject(result.getJSONObject("player"), Player.class);
                    if (listener != null) listener.onSuccess(player);
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

    /**
     *  Get public information about a player.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void getPlayerInfo(@NonNull Playbasis playbasis, @NonNull String playerId, 
                                     final OnResult<Player> listener){
        String uri = playbasis.getServerUrl() + "/Player/" + playerId;
        getPlayer(playbasis, uri, listener);
    }

    /**
     * Get detailed public information about a player, including points and badges.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void getDetailedPlayerListInfo(@NonNull Playbasis playbasis, @NonNull String playerId, 
                                                 final OnResult<Player> listener){
        String uri = playbasis.getServerUrl() + "/Player/" + playerId + "/data/all";
        getPlayer(playbasis, uri, listener);
    }

    /**
     * Get public and private information about a player.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void getPlayerPrivateInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                      final OnResult<Player> listener){
        String uri = playbasis.getServerUrl() + "/Player/" + playerId;
        getPlayerPrivate(playbasis,uri,listener);
    }

    /**
     * Get detailed public and private information about a player, including points and badges
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void getDetailedPlayerPrivateInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                            final OnResult<Player> listener){
        String uri = playbasis.getServerUrl() + "/Player/" + playerId;
        getPlayerPrivate(playbasis,uri,listener);
    }

    /**
     * Get basic information of players. 
     * @param playbasis Playbasis object.
     * @param playersId List of players id.
     * @param listener Callback interface.
     */
    public static void getListPlayerInfo(@NonNull Playbasis playbasis, Collection<String> playersId,
                                            final OnResult<List<Player>> listener){
        String uri = playbasis.getServerUrl() + "/Player/list";
        
        //Prepare list of players id
        List<NameValuePair> params = new ArrayList<>();
        if(playersId!=null){
            String stringPlayersId = "";
            for (String playerId : playersId){
                stringPlayersId+= playerId;
                stringPlayersId+= ",";
            }
            stringPlayersId = StringHelper.removeLastChar(stringPlayersId);
            params.add(new BasicNameValuePair("list_player_id", stringPlayersId));
        }else params = null;
        
        
        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Player> players = JsonHelper.FromJsonArray(result.getJSONArray("player"), Player.class);
                    if(listener!=null)listener.onSuccess(players);
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
    
    public static void register(@NonNull Playbasis playbasis, @NonNull Player player,
                                final OnResult<String> listener){
        if(!player.isValid()){
            if(listener!=null)listener.onError(new HttpError(
                    new RequestError("player not valid", RequestError.ERROR_CODE.DEFAULT)));
            return;
        }
        
        String uri = playbasis.getServerUrl() + "/Player/" + player.getClPlayerId() + "/register";

        JsonObjectPOST(playbasis, uri, player.toParams(), new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                    if(listener!=null)listener.onSuccess("");
            }
            @Override
            public void onError(HttpError error) {
                if(listener!=null)listener.onError(error);
            }
        });

    }
    

    
}
