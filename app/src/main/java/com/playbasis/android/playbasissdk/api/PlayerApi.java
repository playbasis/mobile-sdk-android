package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.helper.StringHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.PlaybasisResponse;
import com.playbasis.android.playbasissdk.http.RequestError;
import com.playbasis.android.playbasissdk.model.Action;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Level;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.Rank;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
    
    private static void getAction(@NonNull Playbasis playbasis,@NonNull String uri, final OnResult<Action> listener) {
        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Action action = JsonHelper.FromJsonObject(result.getJSONObject("action"), Action.class);
                    if (listener != null) listener.onSuccess(action);
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
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId;
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
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/data/all";
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
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId;
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
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId;
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
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL +"list";
        
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
        
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + player.getClPlayerId() + "/register";

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

    public static void update(@NonNull Playbasis playbasis, @NonNull Player player,
                                final OnResult<String> listener){
        if(!player.isValid()){
            if(listener!=null)listener.onError(new HttpError(
                    new RequestError("player not valid", RequestError.ERROR_CODE.DEFAULT)));
            return;
        }

        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + player.getClPlayerId() + "/update";

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
    
    public static void delete(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<String> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/delete";

        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
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

    public static void login(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<String> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/login";

        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
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


    public static void logout(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<String> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/logout";

        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
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

    public static void points(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Point>> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/points";

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Point> points = JsonHelper.FromJsonArray(result.getJSONArray("points"), Point.class);
                    if (listener != null) listener.onSuccess(points);
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

    public static void point(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String pointName,
                              final OnResult<Point> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/point/" + pointName;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Point point = JsonHelper.FromJsonObject(result.getJSONObject("point"), Point.class);
                    if (listener != null) listener.onSuccess(point);
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

    public static void pointHistory(@NonNull Playbasis playbasis, @NonNull String playerId, 
                                    String pointName, Integer offset, Integer limit,
                             final OnResult<List<Point>> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/point_history";

        List<NameValuePair> params = new ArrayList<>();
        if(pointName!=null)params.add(new BasicNameValuePair("point_name", pointName));
        if(offset!=null)params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        if(offset!=null)params.add(new BasicNameValuePair("limit", String.valueOf(limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Point> points = JsonHelper.FromJsonArray(result.getJSONArray("points"), Point.class);
                    if (listener != null) listener.onSuccess(points);
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
    
    public static void actionTime(@NonNull Playbasis playbasis,@NonNull String playerId, @NonNull String actionName, 
                                  final OnResult<Action> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/action/"+
                actionName + "/time";
        getAction(playbasis, uri, listener);
    }

    public static void actionLast(@NonNull Playbasis playbasis,@NonNull String playerId,
                                  final OnResult<Action> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/action/time";
        getAction(playbasis, uri, listener);
    }

    public static void actionCount(@NonNull Playbasis playbasis,@NonNull String playerId,
                                  final OnResult<Action> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/action/time";
        getAction(playbasis,uri,listener);
    }

    public static void badges(@NonNull Playbasis playbasis,@NonNull String playerId,
                              final OnResult<List<Badge>> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + playerId + "/badge";


        JsonArrayGET(playbasis, uri, null, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                    List<Badge> badges = JsonHelper.FromJsonArray(result, Badge.class);
                    if (listener != null) listener.onSuccess(badges);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void level(@NonNull Playbasis playbasis,@NonNull String levelNo,
                              final OnResult<Level> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + "level/" + levelNo;


        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Level level = JsonHelper.FromJsonObject(result, Level.class);
                if (listener != null) listener.onSuccess(level);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void levels(@NonNull Playbasis playbasis, final OnResult<List<Level>> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + "level";


        JsonArrayGET(playbasis, uri, null, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                List<Level> levels = JsonHelper.FromJsonArray(result, Level.class);
                if (listener != null) listener.onSuccess(levels);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }
    
    //TODO: Claim badge
    public static void claimBage(@NonNull Playbasis playbasis){
        
        
    }
    //TODO: Redeem badge
    public static void redeemBadge(@NonNull Playbasis playbasis){


    }
    
    //TODO: Rank
     public static void rank(@NonNull Playbasis playbasis){
         
         
     }
    
    //TODO: Ranks
    public static void ranks(@NonNull Playbasis playbasis, Integer limit,  final OnResult<Rank> listener){
        if(limit == null) limit = 20;
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + "ranks/" + limit;


        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Rank rank = JsonHelper.FromJsonObject(result, Rank.class);
                if (listener != null) listener.onSuccess(rank);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });

    }
    
    //TODO: Goods
    public static void goods(@NonNull Playbasis playbasis){


    }
    
    //TODO: quest of player
    public static void quest(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String questId,
                             final OnResult<Quest> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + "quest/" + questId;
        
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Quest quest = JsonHelper.FromJsonObject(result.getJSONObject("quest"), Quest.class);
                    if (listener != null) listener.onSuccess(quest);
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
    
    //TODO: List of quests of player
    public static void quests(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Quest>> listener){
        String uri = playbasis.getServerUrl() + SDKUtil._PLAYER_URL + "quest";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Quest> quests = JsonHelper.FromJsonArray(result.getJSONArray("quests"), Quest.class);
                    if (listener != null) listener.onSuccess(quests);
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
    
    //TODO: quest rewards
    public static void questReward(@NonNull Playbasis playbasis){


    }
    
    //TODO: Deduct rewards
    public static void deductReward(@NonNull Playbasis playbasis){


    }
}
