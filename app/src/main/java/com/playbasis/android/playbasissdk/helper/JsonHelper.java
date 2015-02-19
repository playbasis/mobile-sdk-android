package com.playbasis.android.playbasissdk.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.playbasis.android.playbasissdk.http.toolbox.ParameterizedList;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.model.TokenValue;
import com.playbasis.android.playbasissdk.parser.RewardTypeArrayAdapter;
import com.playbasis.android.playbasissdk.parser.TokenValueArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class JsonHelper {
    public static final String TAG = "JsonHelper";

    public static <T> List<T> FromJsonArray(JSONArray jsonArray, Class<T> klass) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Reward.class, new RewardTypeArrayAdapter());
        gsonBuilder.registerTypeAdapter(TokenValue.class, new TokenValueArrayAdapter());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonArray.toString(), new ParameterizedList<T>(klass));

    }

    public static <T> T FromJsonObject(JSONObject jsonObject, Class<T> type){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Reward.class, new RewardTypeArrayAdapter());
        gsonBuilder.registerTypeAdapter(TokenValue.class, new TokenValueArrayAdapter());
        Gson gson = gsonBuilder.create();
        T item = gson.fromJson(jsonObject.toString(), type);
        return item;
    }

}
