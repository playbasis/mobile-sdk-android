package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.playbasis.android.playbasissdk.model.CustomRank;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Level;
import com.playbasis.android.playbasissdk.model.Node;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.PointDetail;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.Ranks;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.model.ReferralCode;
import com.playbasis.android.playbasissdk.model.Role;
import com.playbasis.android.playbasissdk.model.SaleReport;
import com.playbasis.android.playbasissdk.model.Session;
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
    protected static final String LIST_PLAYER_ID = "list_player_id";
    public static final String SESSIONS = "sessions";
    public static final String SESSION_ID = "session_id";
    public static final String SETUP_PHONE = "setupPhone";

    private static void getPlayer(@NonNull Playbasis playbasis, String uri, final OnResult<Player> listener) {
        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Player player = JsonHelper.FromJsonObject(result.getJSONObject(ApiConst.PLAYER), Player.class);
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

    private static void getPlayerPrivate(@NonNull Playbasis playbasis, @NonNull String uri,
                                            final OnResult<Player> listener) {
        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Player player = JsonHelper.FromJsonObject(result.getJSONObject(ApiConst.PLAYER), Player.class);
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
                    Action action = JsonHelper.FromJsonObject(result.getJSONObject(ApiConst.ACTION), Action.class);
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId;
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+"data/all";
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId;
        getPlayerPrivate(playbasis, uri, listener);
    }

    /**
     * Get detailed public and private information about a player, including points and badges
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void detailedPlayerPrivateInfo(@NonNull Playbasis playbasis, @NonNull String playerId,
                                                 final OnResult<Player> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+"data/all";
        getPlayerPrivate(playbasis, uri, listener);
    }

    /**
     * Get basic information of players. 
     * @param playbasis Playbasis object.
     * @param playersId List of players id.
     * @param listener Callback interface.
     */
    public static void listPlayerInfo(@NonNull Playbasis playbasis, Collection<String> playersId,
                                      final OnResult<List<Player>> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL +"list";
        
        //Prepare list of players id
        List<NameValuePair> params = new ArrayList<>();
        if(playersId!=null){
            String stringPlayersId = "";
            for (String playerId : playersId){
                stringPlayersId+= playerId;
                stringPlayersId+= ",";
            }
            stringPlayersId = StringHelper.removeLastChar(stringPlayersId);
            params.add(new BasicNameValuePair(LIST_PLAYER_ID, stringPlayersId));
        }else params = null;


        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Player> players = JsonHelper.FromJsonArray(result.getJSONArray(ApiConst.PLAYER), Player.class);
                    if (listener != null) listener.onSuccess(players);
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
     * Register a user from client's website as a Playbasis player.
     * @param playbasis Playbasis object.
     * @param player Id of the player.
     * @param listener Callback interface.
     */
    public static void register(@NonNull Playbasis playbasis, @NonNull Player player,
                                final OnResult<Boolean> listener){
        register(playbasis, false, player, listener);
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
                            new RequestError(ApiConst.PLAYER_NOT_VALID, RequestError.ERROR_CODE.DEFAULT)));
                   
                    return;
                }
            }

            AbstractPlayerView playerView = playbasis.getPlayerView() == null ? new PlayerView() : playbasis.getPlayerView();
            playerView.setPlayer(player==null? new Player() : player);
            playerView.show(fm, ApiConst.FRAGMENT_PLAYER_INFO);
            playerView.setPlayerListener(new PlayerView.OnPlayer() {
                @Override
                public void onPlayer(Player player) {
                    registerPlayer(playbasis, isAsync, player, listener);
                }
            });
        }else{
            registerPlayer(playbasis, isAsync, player, listener);
        }
    }


    protected static boolean verifyEmail(@NonNull Playbasis playbasis, boolean isAsync, @NonNull Player player,
                                         final OnResult<Boolean> listener) {
        //todo dkp-119
        String verifyEmail = SDKUtil._PLAYER_URL + player.getClPlayerId() +"/";
        final String[] verifyEmailResult = new String[1];//=null;


        if (isAsync) {

            JSONObject jsonObjectPlayer = null;
            try {
                jsonObjectPlayer = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObjectPlayer = player.toJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //todo dkp-119
            asyncPost(playbasis, verifyEmail, jsonObjectPlayer, new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(true);
                    verifyEmailResult[0] = result;
             //   ....
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });
        } else {
            String uri = playbasis.getUrl() + verifyEmail;
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
        return false;
        }


    protected static void registerPlayer(@NonNull Playbasis playbasis, boolean isAsync, @NonNull Player player,
                                final OnResult<Boolean> listener){

        String endpoint = SDKUtil._PLAYER_URL + player.getClPlayerId() +"/"+ ApiConst.REGISTER;

        if(isAsync){

            JSONObject jsonObjectPlayer = null;
            try {
                jsonObjectPlayer = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObjectPlayer = player.toJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }



            asyncPost(playbasis, endpoint, jsonObjectPlayer, new OnResult<String>() {
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
            String uri = playbasis.getUrl() + endpoint;
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
        
            if (!player.isValidForUpdate()) {
                FragmentManager fm;
                if (playbasis.getActivity() != null) {
                    fm = playbasis.getActivity().getSupportFragmentManager();
                } else {
                    try {
                        fm = ((FragmentActivity) playbasis.getContext()).getSupportFragmentManager();
                    } catch (ClassCastException e) {
                        if (listener != null) listener.onError(new HttpError(
                                new RequestError(ApiConst.PLAYER_NOT_VALID, RequestError.ERROR_CODE.DEFAULT)));
                        return;
                    }
                }
            
            PlayerView playerView = new PlayerView();
            playerView.setPlayer(player);
            playerView.show(fm, ApiConst.FRAGMENT_PLAYER_INFO);
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
        if(!player.isValidForUpdate()){
            if(listener!=null)listener.onError(new HttpError(
                    new RequestError(ApiConst.PLAYER_NOT_VALID, RequestError.ERROR_CODE.DEFAULT)));
            return;
        }

        String endpoint = SDKUtil._PLAYER_URL + player.getClPlayerId() +"/"+ ApiConst.UPDATE;

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject = player.toJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            asyncPost(playbasis, endpoint, jsonObject, new OnResult<String>() {
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


            String uri = playbasis.getUrl() + endpoint;

            JsonArrayPOST(playbasis, uri, player.toParamsForUpdate(), new OnResult<JSONArray>() {
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

        String endpoint =  SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.DELETE;

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

            String uri = playbasis.getUrl() + endpoint;

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
        login(playbasis, false, playerId, null, null, listener);
    }

    public static void login(@NonNull Playbasis playbasis, @NonNull String playerId,
                             @Nullable String sessionId, @Nullable Integer sessionExpiresIn, final OnResult<Boolean> listener){
        login(playbasis, false, playerId, sessionId, sessionExpiresIn, listener);
    }

    /**
     * {@link #delete(com.playbasis.android.playbasissdk.core.Playbasis, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void login(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId, @Nullable String sessionId,
                             @Nullable Integer sessionExpiresIn, final OnResult<Boolean> listener){

        String endpoint =  SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.LOGIN;

        List<NameValuePair> params = new ArrayList<>();
        if(sessionId!=null)params.add(new BasicNameValuePair(ApiConst.SESSION_ID, sessionId));
        if(sessionExpiresIn!=null)params.add(new BasicNameValuePair(ApiConst.SESSION_EXPIRES_IN, String.valueOf(sessionExpiresIn)));

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

            String uri = playbasis.getUrl() + endpoint;


            JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
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
     *  Tell Playbasis system that a player has logged out.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param sessionId session ID
     * @param listener Callback interface.
     */
    public static void logout(@NonNull Playbasis playbasis, @NonNull String playerId, @Nullable String sessionId,
                              final OnResult<Boolean> listener){
        logout(playbasis, false, playerId, sessionId,listener);
    }

    /**
     * {@link logout(com.playbasis.android.playbasissdk.core.Playbasis, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId Id of the player.
     * @param sessionId session ID
     * @param listener Callback interface.
     */
    public static void logout(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId,
                              @Nullable String sessionId ,final OnResult<Boolean> listener){

        String endpoint =  SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.LOGOUT;

        List<NameValuePair> params = new ArrayList<>();
        if(sessionId != null) params.add(new BasicNameValuePair("session_id", sessionId));

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                if(sessionId != null)  jsonObject.put("session_id", sessionId);
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


        } else {

            String uri = playbasis.getUrl() + endpoint;

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
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
     *  List active sessions of a player in Playbasis system.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void listActivePlayerSessions(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Session>> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ SESSIONS;

        JsonArrayGET(playbasis, uri, null, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                List<Session> sessions = JsonHelper.FromJsonArray(result, Session.class);
                if (listener != null) listener.onSuccess(sessions);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     *  Find a player given session ID.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void findPlayerBySession(@NonNull Playbasis playbasis, @NonNull String playerId,
                                                final OnResult<List<Player>> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ SESSION_ID;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Player> players = JsonHelper.FromJsonArray(result.getJSONArray(SESSION_ID), Player.class);
                    if (listener != null) listener.onSuccess(players);
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
     *  Returns information about all point-based rewards that a player currently have.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param listener Callback interface.
     */
    public static void points(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Point>> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.POINTS;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Point> points = JsonHelper.FromJsonArray(result.getJSONArray(ApiConst.POINTS), Point.class);
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.POINT +"/" + pointName;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Point> points = JsonHelper.FromJsonArray(result.getJSONArray(ApiConst.POINT), Point.class);
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.POINT_HISTORY;

        List<NameValuePair> params = new ArrayList<>();
        if(pointName!=null)params.add(new BasicNameValuePair(ApiConst.POINT_NAME, pointName));
        if(offset!=null)params.add(new BasicNameValuePair(ApiConst.OFFSET, String.valueOf(offset)));
        if(offset!=null)params.add(new BasicNameValuePair(ApiConst.LIMIT, String.valueOf(limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<PointDetail> points = JsonHelper.FromJsonArray(result.getJSONArray(ApiConst.POINTS), PointDetail.class);
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.ACTION +"/"+
                actionName +"/"+ ApiConst.TIME;
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+"action/time";
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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.ACTION +"/" + actionName +"/"+ ApiConst.COUNT;
        getAction(playbasis, uri, listener);
    }

    /**
     *  Returns information about all the badges that a player has earned.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void badges(@NonNull Playbasis playbasis,@NonNull String playerId,
                              final OnResult<List<Badge>> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.BADGE;


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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + ApiConst.LEVEL +"/" + levelNo;


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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + ApiConst.LEVELS;


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
                                  @NonNull String badgeId, final OnResult<Boolean> listener) {
        claimBadge(playbasis, false, playerId, badgeId, listener);
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
        String endpoint =  SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.BADGE +"/" + badgeId +"/"+ ApiConst.CLAIM;
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

            String uri = playbasis.getUrl() + endpoint;
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

        String endpoint =   SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.BADGE+"/" + badgeId +"/"+ ApiConst.REDEEM;
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

            String uri = playbasis.getUrl() + endpoint;

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
         String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + ApiConst.RANK +"/" + rankBy +"/" + limit;


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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + ApiConst.RANKS +"/" + limit;


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

        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.GOODS;


        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Goods> goodses = JsonHelper.FromJsonArray(result.getJSONArray(ApiConst.GOODS), Goods.class);
                    if (listener != null) listener.onSuccess(goodses);
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
     *  Quest that the player has joined.
     * @param playbasis Playbasis object.
     * @param playerId Id of the player.
     * @param questId Id of the question.
     * @param listener Callback interface.
     */
    public static void quest(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String questId,
                             final OnResult<Quest> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + ApiConst.QUEST +"/" + questId;
        
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Quest quest = JsonHelper.FromJsonObject(result.getJSONObject(ApiConst.QUEST), Quest.class);

                    JSONObject questJsonObject = result.getJSONObject(ApiConst.QUEST);
                    if (questJsonObject.optJSONArray(ApiConst.CONDITION) != null){
                        JSONArray conditions = questJsonObject.getJSONArray(ApiConst.CONDITION);
                        for(int j = 0; j < conditions.length(); j++) {
                            JSONObject condition = conditions.getJSONObject(j);
                            if (condition.getString(ApiConst.CONDITION_TYPE).equals(ApiConst.DATETIME_START)){
                                quest.setDateStart(condition.getString(ApiConst.CONDITION_VALUE));
                            }

                            if (condition.getString(ApiConst.CONDITION_TYPE).equals(ApiConst.DATETIME_END)){
                                quest.setDateEnd(condition.getString(ApiConst.CONDITION_VALUE));

                            }
                        }
                    }

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
                              @Nullable List<String> filters, final OnResult<List<Quest>> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + ApiConst.QUEST;
        String filterString = "";
        if(filters != null) {
            for (int i = 0; i < filters.size(); i++) {
                if (i > 0) {
                    filterString = filterString + ",";
                }
                filterString = filterString + filters.get(i);
            }
        }

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
        if (filters != null) {
            params.add(new BasicNameValuePair(ApiConst.FILTER, filterString));
        }

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray questsJsonArray = result.getJSONArray(ApiConst.QUESTS);
                    List<Quest> quests = new ArrayList<Quest>();
                    for (int i = 0; i < questsJsonArray.length(); i++) {
                        JSONObject questJsonObject = questsJsonArray.getJSONObject(i);
                        Quest quest = JsonHelper.FromJsonObject(questJsonObject, Quest.class);
                        if (questJsonObject.optJSONArray(ApiConst.CONDITION) != null){
                            JSONArray conditions = questJsonObject.getJSONArray(ApiConst.CONDITION);
                            for(int j = 0; j < conditions.length(); j++) {
                                JSONObject condition = conditions.getJSONObject(j);
                                if (condition.getString(ApiConst.CONDITION_TYPE).equals(ApiConst.DATETIME_START)){
                                    quest.setDateStart(condition.getString(ApiConst.CONDITION_VALUE));
                                }

                                if (condition.getString(ApiConst.CONDITION_TYPE).equals(ApiConst.DATETIME_END)){
                                    quest.setDateEnd(condition.getString(ApiConst.CONDITION_VALUE));

                                }
                            }
                        }
                        quests.add(quest);
                    }
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

    public static void questsAll(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Quest>> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + ApiConst.QUEST_ALL +"/" + playerId;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray questsJsonArray = result.getJSONArray(ApiConst.QUESTS);
                    List<Quest> quests = new ArrayList<Quest>() ;
                    for (int i = 0; i < questsJsonArray.length(); i++) {
                        JSONObject questJsonObject = questsJsonArray.getJSONObject(i);
                        Quest quest = JsonHelper.FromJsonObject(questJsonObject, Quest.class);
                        if (questJsonObject.optJSONArray(ApiConst.CONDITION) != null){
                            JSONArray conditions = questJsonObject.getJSONArray(ApiConst.CONDITION);
                            for(int j = 0; j < conditions.length(); j++) {
                                JSONObject condition = conditions.getJSONObject(j);
                                if (condition.getString(ApiConst.CONDITION_TYPE).equals(ApiConst.DATETIME_START)){
                                    quest.setDateStart(condition.getString(ApiConst.CONDITION_VALUE));
                                }

                                if (condition.getString(ApiConst.CONDITION_TYPE).equals(ApiConst.DATETIME_END)){
                                    quest.setDateEnd(condition.getString(ApiConst.CONDITION_VALUE));

                                }
                            }
                        }
                        quests.add(quest);
                    }

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
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.QUEST_REWARD_HISTORY;

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
        params.add(new BasicNameValuePair(ApiConst.OFFSET, String.valueOf(offset==null ? 0 : offset)));
        params.add(new BasicNameValuePair(ApiConst.LIMIT, String.valueOf(limit==null ? 50 : limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Reward> rewards = JsonHelper.FromJsonArray(result.getJSONArray(ApiConst.REWARDS), Reward.class);
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
                                    final OnResult<String> listener) {
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


        String endpoint =    SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.DEDUCT_REWARD;
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.REWARD, rewardId);
                jsonObject.put(ApiConst.AMOUNT, String.valueOf(amount));
                jsonObject.put(ApiConst.FORCE, String.valueOf(force == null ? 0 : force ? 1 : 0));
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
            params.add(new BasicNameValuePair(ApiConst.REWARD, rewardId));
            params.add(new BasicNameValuePair(ApiConst.AMOUNT, String.valueOf(amount)));
            params.add(new BasicNameValuePair(ApiConst.FORCE, String.valueOf(force == null ? 0 : force ? 1 : 0)));


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
    /**
     * Get unique referral code of player.
     * @param playbasis Playbasis object.
     * @param uri String uri.
     * @param listener Callback interface.
     */
    private static void getPlayerReferralCode(@NonNull Playbasis playbasis, String uri, final OnResult<ReferralCode> listener) {
        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ReferralCode referralCode = JsonHelper.FromJsonObject(result, ReferralCode.class);
                if (listener != null) listener.onSuccess(referralCode);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }
    /**
     * Get unique referral code of player.
     * @param playbasis Playbasis object.
     * @param playerId player id.
     * @param listener Callback interface.
     */
    public static void referralCode(@NonNull Playbasis playbasis, @NonNull String playerId,
                                              final OnResult<ReferralCode> listener){
        String uri = playbasis.getUrl() + SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.CODE;
        getPlayerReferralCode(playbasis, uri, listener);
    }

    public static void auth(@NonNull Playbasis playbasis, String email, String username,
                            @NonNull String password, String deviceId, final OnResult<String> listener) {
        auth(playbasis, false, email, username, password, deviceId, listener);
    }


    /**
     *
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param email User's email.
     * @param username User's username.
     * @param password User's password.
     * @param deviceId User's deviceId
     * @param listener Callback Interface
     */
    private static void auth(@NonNull Playbasis playbasis, boolean isAsync, String email, String username,
                            @NonNull String password, String deviceId, final OnResult<String> listener) {

        String endpoint =  SDKUtil._PLAYER_URL + ApiConst.AUTH;
        String uri = playbasis.getUrl() + endpoint;

        List<NameValuePair> params = new ArrayList<>();
        if (email != null) {
            params.add(new BasicNameValuePair(ApiConst.EMAIL, email));
        }
        if (username != null) {
            params.add(new BasicNameValuePair(ApiConst.USERNAME, username));
        }
        if (deviceId != null) {
            params.add(new BasicNameValuePair(ApiConst.DEVICE_ID, deviceId));
        }
        params.add(new BasicNameValuePair(ApiConst.PASSWORD, password));

        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) {
                    try {
                        String sessionId = result.getString(ApiConst.SESSION_ID);
                        listener.onSuccess(sessionId);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(HttpError error) {
                error.printStackTrace();
                if (error.getMessage() != null) {
                    System.out.println(error.getMessage());
                }
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void resetPasswordByEmail(@NonNull Playbasis playbasis, @NonNull String email, final OnResult<Boolean> listener) {
        String endpoint =  SDKUtil._PLAYER_URL +"/"+ ApiConst.PASSWORD+"/"+"+"+ ApiConst.EMAIL;
        String uri = playbasis.getUrl() + endpoint;

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(ApiConst.EMAIL, String.valueOf(email)));

        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) {
                    listener.onSuccess(true);
                }
            }

            @Override
            public void onError(HttpError error) {
                error.printStackTrace();
                if (error.getMessage() != null) {
                    System.out.println(error.getMessage());
                }
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void requestOtp(@NonNull Playbasis playbasis,@NonNull String playerId, final OnResult<String> listener) {
        requestOtp(playbasis, false, playerId, listener);
    }


    private static void requestOtp(@NonNull Playbasis playbasis, boolean isAsync,@NonNull String playerId, final OnResult<String> listener) {

        String endpoint =  SDKUtil._PLAYER_URL + ApiConst.AUTH+"/" + playerId +"/"+ ApiConst.REQUEST_OTPCODE;
        String uri = playbasis.getUrl() + endpoint;

        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) {
                    String otp = null;
                    try {
                        otp = result.getString(ApiConst.CODE);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    } finally {
                        listener.onSuccess(otp);
                    }
                }
            }

            @Override
            public void onError(HttpError error) {
                error.printStackTrace();
                if (error.getMessage() != null) {
                    System.out.println(error.getMessage());
                }
                if (listener != null) listener.onError(error);
            }
        });
    }


    private static void requestOtpForSetupPhone(@NonNull Playbasis playbasis, boolean isAsync,@NonNull String playerId, final OnResult<String> listener) {

        String endpoint =  SDKUtil._PLAYER_URL + ApiConst.AUTH+"/" + playerId +"/"+ SETUP_PHONE;
        String uri = playbasis.getUrl() + endpoint;

        JsonObjectPOST(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (listener != null) {
                    String otp = null;
                    try {
                        otp = result.getString(ApiConst.CODE);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    } finally {
                        listener.onSuccess(otp);
                    }
                }
            }

            @Override
            public void onError(HttpError error) {
                error.printStackTrace();
                if (error.getMessage() != null) {
                    System.out.println(error.getMessage());
                }
                if (listener != null) listener.onError(error);
            }
        });
    }


    public static void verifyOtp(@NonNull Playbasis playbasis,@NonNull String playerId,@NonNull String code, final OnResult<Boolean> listener) {
        verifyOtp(playbasis, false, playerId, code, listener);
    }


    private static void verifyOtp(@NonNull Playbasis playbasis, boolean isAsync,@NonNull String playerId,@NonNull String code, final OnResult<Boolean> listener) {

        String endpoint =  SDKUtil._PLAYER_URL + ApiConst.AUTH+"/" + playerId +"/"+ ApiConst.VERIFY_OTPCODE;
        String uri = playbasis.getUrl() + endpoint;

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(ApiConst.CODE, code));

        JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                if (listener != null) listener.onSuccess(true);
            }

            @Override
            public void onError(HttpError error) {
                error.printStackTrace();
                if (error.getMessage() != null) {
                    System.out.println(error.getMessage());
                }
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void associatedNode(@NonNull Playbasis playbasis, @NonNull String playerId, final OnResult<ArrayList<Node>> listener) {

        String endpoint =  SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.GET_ASSOCIATED_NODE;
        String uri = playbasis.getUrl() + endpoint;

        JsonArrayGET(playbasis, uri, null, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<Node> nodes = new ArrayList<Node>();
                try {
                    for (int i = 0; i < result.length(); i++) {
                        Node node = new Node();
                        JSONObject jsonObject = result.getJSONObject(i);
                        node.setId(jsonObject.getString(ApiConst.NODE_ID));
                        node.setName(jsonObject.getString(ApiConst.NAME));
                        nodes.add(node);
                        System.out.println(node);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (listener != null) listener.onSuccess(nodes);
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     *  Returns roles of players for given node id
     * @param playbasis Playbasis object.
     * @param playerId player id to get the role
     * @param nodeId node id to get role
     * @param listener Callback interface.
     */
    public static void getRole(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String nodeId,final OnResult<ArrayList<Role>> listener) {
        String endpoint =  SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.GET_ROLE +"/" + nodeId +"/";
        String uri = playbasis.getUrl() + endpoint;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ArrayList<Role> roles = new ArrayList<Role>();
                try {
                    JSONArray jsonArray = result.getJSONArray(ApiConst.ROLES);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        Role role = JsonHelper.FromJsonObject(jsonArray.getJSONObject(i), Role.class);
                        roles.add(role);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (listener != null) {
                        listener.onSuccess(roles);
                    }
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void saleReport(@NonNull Playbasis playbasis, @NonNull String playerId, Integer month, Integer year, String action, String parameter, final OnResult<ArrayList<SaleReport>> listener) {
        String endpoint =  SDKUtil._PLAYER_URL + playerId +"/"+ ApiConst.SALE_REPORT +"/";
        String uri = playbasis.getUrl() + endpoint;

        List<NameValuePair> params = new ArrayList<>();
        if(month!=null)params.add(new BasicNameValuePair(ApiConst.MONTH, String.valueOf(month)));
        if(year!=null)params.add(new BasicNameValuePair(ApiConst.YEAR, String.valueOf(year)));
        if(action!=null)params.add(new BasicNameValuePair(ApiConst.ACTION, action));
        if(parameter!=null)params.add(new BasicNameValuePair(ApiConst.PARAMETER, parameter));

        JsonArrayGET(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<SaleReport> saleReports = new ArrayList<SaleReport>();
                try {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        SaleReport report = new SaleReport();
                        report.setNodeId(jsonObject.getString(ApiConst.NODE_ID));
                        report.setNodeName(jsonObject.getString(ApiConst.NAME));
                        report.setAmount(jsonObject.getInt(ApiConst.AMOUNT));
                        report.setPreviousAmount(jsonObject.getInt(ApiConst.PREVIOUS_AMOUNT));
                        report.setPercentChanged(jsonObject.getDouble(ApiConst.PERCENT_CHANGED));
                        saleReports.add(report);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (listener != null) listener.onSuccess(saleReports);
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    /**
     *  Returns list of players sorted by the specified action type.
     * @param playbasis Playbasis object.
     * @param action name of action to rank players by.
     * @param parameter name of parameter to rank player by
     * @param limit Number of results to return.
     * @param month month to return ranks.
     * @param year year to return ranks.
     * @param listener Callback interface.
     */
    public static void customRank(@NonNull Playbasis playbasis, @NonNull String action, final @NonNull String parameter,
                                  Integer limit, Integer month, Integer year, final OnResult< ArrayList<CustomRank>> listener) {
        String endpoint =  SDKUtil._PLAYER_URL +"/"+ ApiConst.RANK_PARAM +"/" + action +"/" + parameter  +"/";
        String uri = playbasis.getUrl() + endpoint;

        List<NameValuePair> params = new ArrayList<>();
        if(month!=null)params.add(new BasicNameValuePair(ApiConst.MONTH, String.valueOf(month)));
        if(year!=null)params.add(new BasicNameValuePair(ApiConst.YEAR, String.valueOf(year)));
        if(limit!=null)params.add(new BasicNameValuePair(ApiConst.LIMIT, String.valueOf(limit)));

        JsonArrayGET(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                ArrayList<CustomRank> ranks = new ArrayList<CustomRank>();
                try {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        CustomRank customRank = JsonHelper.FromJsonObject(jsonObject, CustomRank.class);
                        customRank.setCustomRankName(parameter);
                        Integer value = jsonObject.getInt(parameter);
                        customRank.setCustomRankValue(value);
                        ranks.add(customRank);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (listener != null) listener.onSuccess(ranks);
                }
            }

            @Override
            public void onError (HttpError error){
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void getPlayerBysessionId(@NonNull Playbasis playbasis, @NonNull String sessionId, final  OnResult<Player> listener) {
        String endpoint =  SDKUtil._PLAYER_URL +"/"+ ApiConst.SESSION +"/" + sessionId;
        String uri = playbasis.getUrl() + endpoint;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Player player = JsonHelper.FromJsonObject(result, Player.class);
                if(listener != null) listener.onSuccess(player);
            }

            @Override
            public void onError(HttpError error) {
                if(listener != null) listener.onError(error);
            }
        });
    }
}
