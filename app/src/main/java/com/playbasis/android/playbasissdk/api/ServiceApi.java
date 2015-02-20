package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.Rule;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class ServiceApi extends Api{
    public static final String TAG = "ServiceApi";


    public static void recentPoint(@NonNull Playbasis playbasis, String pointName,
                                   Integer offset, Integer limit, final OnResult<List<Point>> listener) {
        
        String uri = SDKUtil.SERVER_URL + SDKUtil._SERVICE_URL + "recent_point";

        List<NameValuePair> params = new ArrayList<>();
        if(pointName!=null)params.add(new BasicNameValuePair("point_name", pointName));
        if(offset!=null)params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        if(limit!=null)params.add(new BasicNameValuePair("limit", String.valueOf(limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Point> points = JsonHelper.FromJsonArray(result.getJSONArray("points"), Point.class);
                    if (listener != null) listener.onSuccess(points);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }
                
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }


    public static void resetPoint(@NonNull Playbasis playbasis, boolean isAsync,
                             String pointName, final OnResult<String> listener){

        String endpoint = SDKUtil._SERVICE_URL + "reset_point";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                if(pointName!=null) jsonObject.put("point_name", pointName);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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
            if (pointName != null) params.add(new BasicNameValuePair("point_name", pointName));

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    if (listener != null) listener.onSuccess(result.toString());
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });

        }
    }
}
