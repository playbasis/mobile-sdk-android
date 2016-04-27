package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Content;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pongsakorn on 1/18/2016.
 */
public class ContentApi extends Api {
    public static final String TAG = "ContentApi";
    public static final String FULL_HTML = "full_html";
    public static final String SORT = "sort";
    public static final String TITLE = "title";

    public static void retrieveContent(@NonNull Playbasis playbasis, String id, String title, String category, String date_check, String sort, String order,
                                 Integer offset, Integer limit, @Nullable Boolean fullHtml,final OnResult<ArrayList<Content>> listener) {
        String uri = playbasis.getUrl() +"/"+"Content";
        List<NameValuePair> params = new ArrayList<>();
        if (id != null) params.add(new BasicNameValuePair(
                ApiConst.ID,id));
        if (title != null) params.add(new BasicNameValuePair(TITLE,title));
        if (category != null) params.add(new BasicNameValuePair(ApiConst.CATEGORY,category));
        if (date_check != null) params.add(new BasicNameValuePair(ApiConst.SEARCH,date_check));
        if (sort != null) params.add(new BasicNameValuePair(SORT,sort));
        if (order != null) params.add(new BasicNameValuePair(ApiConst.ORDER,order));
        if (offset != null) params.add(new BasicNameValuePair(ApiConst.OFFSET, String.valueOf(offset)));
        if (limit != null) params.add(new BasicNameValuePair(ApiConst.LIMIT,String.valueOf(limit)));
        if (fullHtml != null) params.add(new BasicNameValuePair(FULL_HTML,String.valueOf(fullHtml)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                ArrayList<Content> contents = new ArrayList<>();
                try {
                    JSONArray jsonArray = result.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Content content = JsonHelper.FromJsonObject(jsonObject, Content.class);

                        contents.add(content);
                    }
                    listener.onSuccess(contents);
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
}
