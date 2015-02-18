package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Mission;
import com.playbasis.android.playbasissdk.model.Quest;

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
public class QuestApi extends Api {
    public static final String TAG = "QuestApi";

    public static void listInfo(@NonNull Playbasis playbasis , final OnResult<List<Quest>>listener){
        String uri = SDKUtil.getServerUrl(false) + SDKUtil.QUEST_URL;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
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

    public static void info(@NonNull Playbasis playbasis, @NonNull String questId, final OnResult<Quest>listener){
        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUEST_URL + questId;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Quest quest = JsonHelper.FromJsonObject(result.getJSONObject("quests"), Quest.class);
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

    public static void missionInfo(@NonNull Playbasis playbasis, @NonNull String questId, @NonNull String missionId,
                                   final OnResult<Mission>listener){
        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUEST_URL + questId + "/mission/" + missionId;

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
}
