package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.helper.StringHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.RequestError;
import com.playbasis.android.playbasissdk.model.Action;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Level;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.PointDetail;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.Ranks;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.widget.AbstractPlayerView;
import com.playbasis.android.playbasissdk.widget.PlayerView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
    
    private static void getAction(@NonNull Playbasis playbasis, @NonNull String uri, final OnResult<Action> listener) {
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
    public static void playerInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                  final OnResult<Player> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId;
        getPlayer(playbasis, uri, listener);
    }

    /**
     * Get detailed public information about a player, including points and badges.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void detailedPlayerListInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                              final OnResult<Player> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/data/all";
        getPlayer(playbasis, uri, listener);
    }

    /**
     * Get public and private information about a player.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void playerPrivateInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                         final OnResult<Player> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId;
        getPlayerPrivate(playbasis,uri,listener);
    }

    /**
     * Get detailed public and private information about a player, including points and badges
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void detailedPlayerPrivateInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                                 final OnResult<Player> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/data/all";
        getPlayerPrivate(playbasis,uri,listener);
    }

    /**
     * Get basic information of players. 
     * @param playbasis Playbasis object.
     * @param playersId List of players id.
     * @param listener Callback interface.
     */
    public static void listPlayerInfo(@NonNull Playbasis playbasis, Collection<String> playersId,
                                      final OnResult<List<Player>> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL +"list";
        
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

    /**
     * Register a user from client's website as a Playbasis player.
     * @param playbasis Playbasis object.
     * @param player Id of the player.
     * @param listener Callback interface.
     */
    public static void register(@NonNull Playbasis playbasis, @NonNull Player player,
                                final OnResult<Boolean> listener){
        register(playbasis,false,player,listener);
    }

    /**
     * {@link #register(com.playbasis.android.playbasissdk.core.Playbasis, com.playbasis.android.playbasissdk.model.Player, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync  Make the request async.
     * @param player Id of the player.
     * @param listener Callback interface.
     */
    public static void register(@NonNull final Playbasis playbasis, final boolean isAsync, @NonNull Player player,
                                final OnResult<Boolean> listener){

        if (player==null || !player.isValid()) {
            FragmentManager fm;
            if(playbasis.getActivity() !=null){
                fm = playbasis.getActivity().getSupportFragmentManager();
            }else{
                try {
                    fm = ((FragmentActivity) playbasis.getContext()).getSupportFragmentManager();
                } catch (ClassCastException e) {
                    if (listener != null) listener.onError(new HttpError(
                            new RequestError("player not valid", RequestError.ERROR_CODE.DEFAULT)));
                    return;
                }
            }

            AbstractPlayerView playerView = playbasis.getPlayerView() == null ? new PlayerView() : playbasis.getPlayerView();
            playerView.setPlayer(player==null? new Player() : player);
            playerView.show(fm, "fragment_player_info");
            playerView.setPlayerListener(new PlayerView.OnPlayer() {
                @Override
                public void onPlayer(Player player) {
                    registerPlayer(playbasis, isAsync, player,listener);
                }
            });
        }else{
            registerPlayer(playbasis, isAsync, player,listener);
        }
    }

    protected static void registerPlayer(@NonNull Playbasis playbasis, boolean isAsync, @NonNull Player player,
                                final OnResult<Boolean> listener){

        String endpoint = SDKUtil._PLAYER_URL + player.getClPlayerId() + "/register";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject = player.toJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {


            String uri = SDKUtil.SERVER_URL + endpoint;

            JsonObjectPOST(playbasis, uri, player.toParams(), new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });

        }
    }

    /**
     * Update player information.
     * @param playbasis Playbasis object.
     * @param player Id of the player.
     * @param listener Callback interface.
     */
    public static void update(@NonNull Playbasis playbasis, @NonNull Player player,
                              final OnResult<Boolean> listener){
        update(playbasis,false,player,listener);
    }

    /**
     * {@link #update(com.playbasis.android.playbasissdk.core.Playbasis, com.playbasis.android.playbasissdk.model.Player, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param player  Id of the player.
     * @param listener Callback interface.
     */ 
    public static void update(@NonNull final Playbasis playbasis, final boolean isAsync, @NonNull Player player,
                                final OnResult<Boolean> listener){
        
            if (!player.isValid()) {
                FragmentManager fm;
                if (playbasis.getActivity() != null) {
                    fm = playbasis.getActivity().getSupportFragmentManager();
                } else {
                    try {
                        fm = ((FragmentActivity) playbasis.getContext()).getSupportFragmentManager();
                    } catch (ClassCastException e) {
                        if (listener != null) listener.onError(new HttpError(
                                new RequestError("player not valid", RequestError.ERROR_CODE.DEFAULT)));
                        return;
                    }
                }
            
            PlayerView playerView = new PlayerView();
            playerView.setPlayer(player);
            playerView.show(fm, "fragment_player_info");
            playerView.setPlayerListener(new PlayerView.OnPlayer() {
                @Override
                public void onPlayer(Player player) {
                    updatePlayer(playbasis, isAsync, player, listener);
                }
            });
        }else{
            updatePlayer(playbasis, isAsync, player, listener);
        }
    }

    protected static void updatePlayer(@NonNull Playbasis playbasis, boolean isAsync, @NonNull Player player,
                              final OnResult<Boolean> listener){
        if(!player.isValid()){
            if(listener!=null)listener.onError(new HttpError(
                    new RequestError("player not valid", RequestError.ERROR_CODE.DEFAULT)));
            return;
        }

        String endpoint = SDKUtil._PLAYER_URL + player.getClPlayerId() + "/update";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject = player.toJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {


            String uri = SDKUtil.SERVER_URL + endpoint;

            JsonArrayPOST(playbasis, uri, player.toParams(), new OnResult<JSONArray>() {
                @Override
                public void onSuccess(JSONArray result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });

        }
    }

    /**
     * Permenently delete a player from Playbasis database.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void delete(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<Boolean> listener){
        delete(playbasis,false,playerId,listener);
    }

    /**
     * {@link #delete(com.playbasis.android.playbasissdk.core.Playbasis, String, OnResult)}
     * @param playbasis  Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void delete(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId,
                              final OnResult<Boolean> listener){

        String endpoint =  SDKUtil._PLAYER_URL + playerId + "/delete";
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {

            String uri = SDKUtil.SERVER_URL + endpoint;

            JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }

    }

    /**
     *  Tell Playbasis system that a player has logged in.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void login(@NonNull Playbasis playbasis, @NonNull String playerId,
                             final OnResult<Boolean> listener){
        login(playbasis,false,playerId,listener);
    }

    /**
     * {@link #delete(com.playbasis.android.playbasissdk.core.Playbasis, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void login(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId,
                              final OnResult<Boolean> listener){


        String endpoint =  SDKUtil._PLAYER_URL + playerId + "/login";
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {

            String uri = SDKUtil.SERVER_URL + endpoint;

            JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }

    }


    /**
     *  Tell Playbasis system that a player has logged out.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void logout(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<Boolean> listener){
        logout(playbasis,false,playerId,listener);
    }

    /**
     * {@link #logout(com.playbasis.android.playbasissdk.core.Playbasis, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void logout(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId,
                              final OnResult<Boolean> listener){

        String endpoint =  SDKUtil._PLAYER_URL + playerId + "/logout";
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {

            String uri = SDKUtil.SERVER_URL + endpoint;

            JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }

    }

    /**
     *  Returns information about all point-based rewards that a player currently have.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void points(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Point>> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/points";

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

    /**
     *  Returns how much of specified the point-based reward a player currently have.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param pointName Name of the point-based reward to query.
     * @param listener Callback interface.
     */
    public static void point(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String pointName,
                              final OnResult<List<Point>> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/point/" + pointName;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Point> points = JsonHelper.FromJsonArray(result.getJSONArray("point"), Point.class);
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

    /**
     *  Returns history points of player
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param pointName Name of the point-based reward to query.
     * @param offset Number of records starting.
     * @param limit Number of results to return.
     * @param listener Callback interface.
     */
    public static void pointHistory(@NonNull Playbasis playbasis, @NonNull String playerId,
                                    String pointName, Integer offset, Integer limit,
                             final OnResult<List<PointDetail>> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/point_history";

        List<NameValuePair> params = new ArrayList<>();
        if(pointName!=null)params.add(new BasicNameValuePair("point_name", pointName));
        if(offset!=null)params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        if(offset!=null)params.add(new BasicNameValuePair("limit", String.valueOf(limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<PointDetail> points = JsonHelper.FromJsonArray(result.getJSONArray("points"), PointDetail.class);
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

    /**
     *  Returns the last time that player performed the specified action.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param actionName Name of the action to query.
     * @param listener Callback interface.
     */
    public static void actionTime(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String actionName,
                                  final OnResult<Action> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/action/"+
                actionName + "/time";
        getAction(playbasis, uri, listener);
    }

    /**
     *  Returns the time and action that a player last performed.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void actionLast(@NonNull Playbasis playbasis, @NonNull String playerId,
                                  final OnResult<Action> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/action/time";
        getAction(playbasis, uri, listener);
    }

    /**
     *  Returns the number of times that a player has performed the specified action.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param actionName Name of the action to query.
     * @param listener Callback interface.
     */
    public static void actionCount(@NonNull Playbasis playbasis,@NonNull String playerId, @NonNull String actionName,
                                   final OnResult<Action> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/action/" + actionName + "/count";
        getAction(playbasis,uri,listener);
    }

    /**
     *  Returns information about all the badges that a player has earned.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void badges(@NonNull Playbasis playbasis,@NonNull String playerId,
                              final OnResult<List<Badge>> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/badge";


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

    /**
     *  Returns detail of level.
     * @param playbasis Playbasis object.
     * @param levelNo Number of level.
     * @param listener Callback interface.
     */
    public static void level(@NonNull Playbasis playbasis,@NonNull String levelNo,
                             final OnResult<Level> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + "level/" + levelNo;


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

    /**
     *  Returns all detail of level.
     * @param playbasis Playbasis object.
     * @param listener Callback interface.
     */
    public static void levels(@NonNull Playbasis playbasis, final OnResult<List<Level>> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + "levels";


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

    /**
     * Claim a badge that a player has earned.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param badgeId Id for the badge to change the state.
     * @param listener  Callback interface.
     */
    public static void claimBadge(@NonNull Playbasis playbasis, @NonNull String playerId,
                                  @NonNull String badgeId, final OnResult<Boolean> listener){
        claimBadge(playbasis, false, playerId,badgeId,listener);
    }
    
    /**
     * {@link #claimBadge(com.playbasis.android.playbasissdk.core.Playbasis, String, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Boolean id request is on async mode or sync mode.
     * @param playerId Player id as used in client's website.
     * @param badgeId Id for the badge to change the state.
     * @param listener  Callback interface.
     */
    public static void claimBadge(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId,
                                 @NonNull String badgeId, final OnResult<Boolean> listener){
        

        String endpoint =  SDKUtil._PLAYER_URL + playerId + "/badge/" + badgeId + "/claim";
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
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

            String uri = SDKUtil.SERVER_URL + endpoint;
            JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                        if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }

    }

    /**
     * Redeem a badge that a player has claimed.
     * @param playbasis  Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param badgeId Id for the badge to change the state.
     * @param listener  Callback interface.
     */
    public static void redeemBadge(@NonNull Playbasis playbasis, @NonNull String playerId,
                                   @NonNull String badgeId, final OnResult<Boolean> listener){
        redeemBadge(playbasis, false, playerId, badgeId, listener);
    }

    /**
     * {@link #redeemBadge(com.playbasis.android.playbasissdk.core.Playbasis, String, String, OnResult)}
     * @param playbasis  Playbasis object.
     * @param isAsync Boolean id request is on async mode or sync mode.
     * @param playerId Player id as used in client's website.
     * @param badgeId Id for the badge to change the state.
     * @param listener  Callback interface.
     */
    public static void redeemBadge(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId,
                                   @NonNull String badgeId, final OnResult<Boolean> listener){

        String endpoint =   SDKUtil._PLAYER_URL + playerId + "/badge/" + badgeId + "/redeem";
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
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

            String uri = SDKUtil.SERVER_URL + endpoint;

            JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                        if (listener != null) listener.onSuccess(true);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }
    }

    /**
     *  Returns list of players sorted by the specified point type.
     * @param playbasis Playbasis object.
     * @param rankBy name of point-based reward to rank players by.
     * @param limit Number of results to return.
     * @param listener Callback interface.
     */
     public static void rank(@NonNull Playbasis playbasis, @NonNull String rankBy, Integer limit,
                             final OnResult<List<Rank>> listener){
         if(limit == null) limit = 20;
         String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + "rank/" + rankBy + "/" + limit;


         JsonArrayGET(playbasis, uri, null, new OnResult<JSONArray>() {
             @Override
             public void onSuccess(JSONArray result) {
                 List<Rank> ranks = JsonHelper.FromJsonArray(result, Rank.class);
                 if (listener != null) listener.onSuccess(ranks);
             }

             @Override
             public void onError(HttpError error) {
                 if (listener != null) listener.onError(error);
             }
         });
         
     }

    /**
     *  Returns list of players sorted by each point type.
     * @param playbasis Playbasis object.
     * @param limit Limit.
     * @param listener Callback interface.
     */
    public static void ranks(@NonNull Playbasis playbasis, Integer limit,  
                             final OnResult<Ranks> listener){
        if(limit == null) limit = 20;
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + "ranks/" + limit;


        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Ranks rank = JsonHelper.FromJsonObject(result, Ranks.class);
                if (listener != null) listener.onSuccess(rank);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });

    }

    /**
     *
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void goods(@NonNull Playbasis playbasis, @NonNull String playerId, 
                             final OnResult<List<Goods>> listener ){

        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/goods";


        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Goods> goodses = JsonHelper.FromJsonArray(result.getJSONArray("goods"), Goods.class);
                    if(listener!=null)listener.onSuccess(goodses);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(listener!=null)listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });

    }

    /**
     *  Quest that the player has joined.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param questId Id of the question.
     * @param listener Callback interface.
     */
    public static void quest(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String questId,
                             final OnResult<Quest> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + "quest/" + questId;
        
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

    /**
     * List of quests that the player has joined.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void quests(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Quest>> listener){
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + "quest";

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

    /**
     *  
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param offset Number of records starting.
     * @param limit Number of results to return.
     * @param listener Callback interface.
     */
    public static void questReward(@NonNull Playbasis playbasis, @NonNull String playerId, Integer offset, 
                                   Integer limit, final OnResult<List<Reward>> listener) {
        String uri = SDKUtil.SERVER_URL + SDKUtil._PLAYER_URL + playerId + "/quest_reward_history";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("offset", String.valueOf(offset==null ? 0 : offset)));
        params.add(new BasicNameValuePair("limit", String.valueOf(limit==null ? 50 : limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Reward> rewards = JsonHelper.FromJsonArray(result.getJSONArray("rewards"), Reward.class);
                    if (listener != null) listener.onSuccess(rewards);
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
     * Deduct a reward from a given player.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param rewardId The name of the reward.
     * @param amount Amount.
     * @param force false = not force if player has not enough reward to deduct, 
     *              *              true = force to do the deduct (and player's reward becomes zero)
     * @param listener Callback interface.
     */
    public static void deductReward(@NonNull Playbasis playbasis, @NonNull String playerId,
                                    @NonNull String rewardId, @NonNull Integer amount, Boolean force,
                                    final OnResult<String> listener){
        deductReward(playbasis, false, playerId, rewardId, amount, force, listener);
    }


        /**
         * {@link #deductReward(com.playbasis.android.playbasissdk.core.Playbasis, String, String, Integer, Boolean,
         *  OnResult)}
         * @param playbasis Playbasis object.
         * @param isAsync Make the request async.
         * @param playerId Player id as used in client's website.
         * @param rewardId The name of the reward.
         * @param amount Amount.
         * @param force false = not force if player has not enough reward to deduct, 
         *              *              true = force to do the deduct (and player's reward becomes zero)
         * @param listener Callback interface.
         */
    public static void deductReward(@NonNull Playbasis playbasis, boolean isAsync,  @NonNull String playerId,
                                    @NonNull String rewardId, @NonNull Integer amount, Boolean force,  
                                    final OnResult<String> listener){


        String endpoint =    SDKUtil._PLAYER_URL + playerId + "/deduct_reward";
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("reward", rewardId);
                jsonObject.put("amount", String.valueOf(amount));
                jsonObject.put("force", String.valueOf(force == null ? 0 : force ? 1 : 0));
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

            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("reward", rewardId));
            params.add(new BasicNameValuePair("amount", String.valueOf(amount)));
            params.add(new BasicNameValuePair("force", String.valueOf(force == null ? 0 : force ? 1 : 0)));


            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    String event = JsonHelper.FromJsonObject(result, String.class);
                    if (listener != null) listener.onSuccess(event);

                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        }

    }
}
