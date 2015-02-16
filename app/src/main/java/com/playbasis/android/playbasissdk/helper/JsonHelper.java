package com.playbasis.android.playbasissdk.helper;

import com.google.gson.Gson;
import com.playbasis.android.playbasissdk.http.toolbox.ParameterizedList;

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
        Gson gson = new Gson();
        return gson.fromJson(jsonArray.toString(), new ParameterizedList<T>(klass));

    }

    public static <T> T FromJsonObject(JSONObject jsonObject, Class<T> type){
        Gson gson = new Gson();
        T item = gson.fromJson(jsonObject.toString(), type);
        return item;
    }

}
