package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Event;
import com.playbasis.android.playbasissdk.model.Mission;
import com.playbasis.android.playbasissdk.model.Quest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gregoire barret on 2/18/15.
 * For PlayBasisSdk project.
 */
public class QuestApi extends Api {
    public static final String TAG = "QuestApi";

    private static void quests(@NonNull Playbasis playbasis, @NonNull String uri, List<NameValuePair> params,  
                               final OnResult<List<Quest>>listener){
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


    private static void quest(@NonNull Playbasis playbasis, @NonNull String uri, List<NameValuePair> params,
                              final OnResult<Quest>listener){
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

    private static void postQuest(@NonNull Playbasis playbasis, @NonNull String uri,
                              @NonNull String playerId, final OnResult<Event>listener ){

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONObject result) {
                try {
                    Event events = JsonHelper.FromJsonObject(result.getJSONObject("events"), Event.class);
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


    /**
     * Returns information about all quest for the current site.
     * @param playbasis Playbasis object.
     * @param listener Callback interface.
     */
    public static void listInfo(@NonNull Playbasis playbasis , final OnResult<List<Quest>>listener){
        String uri = playbasis.getUrl() + SDKUtil.QUEST_URL;
        quests(playbasis,uri, null,listener);
        
    }

    /**
     *  Returns information about the quest with the specified id.
     * @param playbasis Playbasis object.
     * @param questId quest id to query
     * @param listener Callback interface.
     */
    public static void info(@NonNull Playbasis playbasis, @NonNull String questId, final OnResult<Quest>listener){
        String uri = playbasis.getUrl() + SDKUtil._QUEST_URL + questId;
        quest(playbasis,uri, null,listener);
    }

    /**
     *  Returns information about the mission with the specified id.
     * @param playbasis Playbasis object.
     * @param questId Quest id to query.
     * @param missionId Mission id to query.
     * @param listener Callback interface.
     */
    public static void missionInfo(@NonNull Playbasis playbasis, @NonNull String questId, @NonNull String missionId,
                                   final OnResult<Mission>listener){
        String uri = playbasis.getUrl() + SDKUtil._QUEST_URL + questId + "/mission/" + missionId;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Mission mission = JsonHelper.FromJsonObject(result, Mission.class);
                    if (listener != null) listener.onSuccess(mission);
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
    public static void questsAvailable(@NonNull Playbasis playbasis, @NonNull String playerId,
                                  final OnResult<List<Quest>>listener){
        String uri = playbasis.getUrl() + SDKUtil._QUEST_URL + "available";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        quests(playbasis, uri, params,listener);
    }

    /**
     * Returns information about the quest is available for player.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param questId Quest id to query.
     * @param listener Callback interface.
     */
    public static void questAvailable(@NonNull Playbasis playbasis, @NonNull String playerId, 
                                      @NonNull String questId, final OnResult<Event> listener) {
        String uri = playbasis.getUrl() + SDKUtil._QUEST_URL + questId + "/available";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                    Event event = JsonHelper.FromJsonObject(result, Event.class);
                    if (listener != null) listener.onSuccess(event);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
        
    }

    /**
     * Request access token from playbasis server.
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param questId Quest id as player need to join.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void join(@NonNull Playbasis playbasis, @NonNull String questId,
                            @NonNull String playerId, final OnResult<Event>listener ){
        join(playbasis,false,questId,playerId,listener);
    }

    /**
     * Request access token from playbasis server.
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param questId Quest id as player need to join.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void join(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String questId,
                            @NonNull String playerId, final OnResult<Event>listener ){

        String endpoint = SDKUtil._QUEST_URL + questId + "/join";
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

            String uri = playbasis.getUrl() + endpoint ;
            postQuest(playbasis, uri, playerId, listener);
        }
    }

    /**
     * Player join all available quests.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void joinAll(@NonNull Playbasis playbasis,
                               @NonNull String playerId, final OnResult<Map<String, Object>>listener ){
        joinAll(playbasis,false,playerId,listener);
    }
    /**
     * Player join all available quests.
     * @param playbasis Playbasis object.
     * @param isAsync  Make the request async.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void joinAll(@NonNull Playbasis playbasis, boolean isAsync,
                            @NonNull String playerId, final OnResult<Map<String, Object>>listener ){

        String endpoint = SDKUtil._QUEST_URL + "joinAll";
        
        if(isAsync){

            JSONObject jsonObject = null;
            try {
                 jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
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


        }else{

            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                @SuppressWarnings("unchecked")
                public void onSuccess(JSONObject result) {
                    HashMap<String,Object> events = JsonHelper.FromJsonObject(result, HashMap.class);
                    if (listener != null) listener.onSuccess(events);
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
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param questId Quest id as player need to join.
     * @param playerId Player id as used in client's website.
     * @param listener Callback interface.
     */
    public static void cancel(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String questId,
                            @NonNull String playerId, final OnResult<Event>listener ){

        String endpoint = SDKUtil._QUEST_URL + questId + "/cancel";
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
            postQuest(playbasis, uri, playerId, listener);
        }
    }
}
