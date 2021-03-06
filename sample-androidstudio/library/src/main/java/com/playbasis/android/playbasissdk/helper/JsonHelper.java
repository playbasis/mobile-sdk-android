package com.playbasis.android.playbasissdk.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.playbasis.android.playbasissdk.api.AuthAuthenticator;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.toolbox.ParameterizedList;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.RedeemEvent;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.model.TokenValue;
import com.playbasis.android.playbasissdk.parser.ActionConfigArrayAdapter;
import com.playbasis.android.playbasissdk.parser.PointTypeArrayAdapter;
import com.playbasis.android.playbasissdk.parser.RankTypeArrayAdapter;
import com.playbasis.android.playbasissdk.parser.RendeemEventArrayAdapter;
import com.playbasis.android.playbasissdk.parser.RewardTypeArrayAdapter;
import com.playbasis.android.playbasissdk.parser.TokenValueArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class JsonHelper {
    public static final String TAG = "JsonHelper";

    /**
     * Parse a jsonArray into a list of java objects. 
     * @param jsonArray jsonArray to parse
     * @param klass object class
     * @param <T> object type
     * @return list of java object
     */
    public static <T> List<T> FromJsonArray(JSONArray jsonArray, Class<T> klass) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Reward.class, new RewardTypeArrayAdapter());
        gsonBuilder.registerTypeAdapter(TokenValue.class, new TokenValueArrayAdapter());
        gsonBuilder.registerTypeAdapter(Rank.class, new RankTypeArrayAdapter());
        gsonBuilder.registerTypeAdapter(ActionConfig.class, new ActionConfigArrayAdapter());
        gsonBuilder.registerTypeAdapter(RedeemEvent.class, new RendeemEventArrayAdapter());
        gsonBuilder.registerTypeAdapter(Point.class, new PointTypeArrayAdapter());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonArray.toString(), new ParameterizedList<T>(klass));

    }

    /**
     * Parse a jsonObject into a java objects.  
     * @param jsonObject jsonObject to parse.
     * @param type object class
     * @param <T> object type
     * @return java object
     */
    public static <T> T FromJsonObject(JSONObject jsonObject, Class<T> type){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Reward.class, new RewardTypeArrayAdapter());
        gsonBuilder.registerTypeAdapter(TokenValue.class, new TokenValueArrayAdapter());
        gsonBuilder.registerTypeAdapter(Rank.class, new RankTypeArrayAdapter());
        gsonBuilder.registerTypeAdapter(ActionConfig.class, new ActionConfigArrayAdapter());
        gsonBuilder.registerTypeAdapter(RedeemEvent.class, new RendeemEventArrayAdapter());
        gsonBuilder.registerTypeAdapter(Point.class, new PointTypeArrayAdapter());
        Gson gson = gsonBuilder.create();
        T item = gson.fromJson(jsonObject.toString(), type);
        return item;
    }


    /**
     * Create a new JSONOgject initialize with the authentication token.
     * @param authenticator Authenticator object contain on Playbasis class
     * @return JSONObject
     * @throws JSONException
     */
    public static JSONObject newJsonWithToken(AuthAuthenticator authenticator) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("token", authenticator.getToken());
        return object;
        
    }

}
