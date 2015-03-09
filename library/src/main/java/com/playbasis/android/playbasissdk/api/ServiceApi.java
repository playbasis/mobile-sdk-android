package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.PointDetail;
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


    /**
     * Returns recent activity points of all players.
     * @param playbasis Playbasis object.
     * @param pointName Name of the point-based reward to query.
     * @param offset Number of records starting.
     * @param limit Number of results to return.
     * @param listener Callback interface.
     */
    public static void recentPoint(@NonNull Playbasis playbasis, String pointName,
                                   Integer offset, Integer limit, final OnResult<List<PointDetail>> listener) {
        
        String uri = playbasis.getUrl() + SDKUtil._SERVICE_URL + "recent_point";

        List<NameValuePair> params = new ArrayList<>();
        if(pointName!=null)params.add(new BasicNameValuePair("point_name", pointName));
        if(offset!=null)params.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        if(limit!=null)params.add(new BasicNameValuePair("limit", String.valueOf(limit)));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<PointDetail> points = JsonHelper.FromJsonArray(result.getJSONArray("points"), PointDetail.class);
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

    /**
     * Reset point of all players.
     * @param playbasis  Playbasis object.
     * @param pointName Name of the point-based reward to query.
     * @param listener Callback interface.
     */
    public static void resetPoint(@NonNull Playbasis playbasis,
                                  String pointName, final OnResult<String> listener){
        resetPoint(playbasis,false,pointName,listener);
    }
    /**
     * {@link #resetPoint(com.playbasis.android.playbasissdk.core.Playbasis, String, OnResult)}
     * @param playbasis  Playbasis object.
     * @param isAsync Make the request async.
     * @param pointName Name of the point-based reward to query.
     * @param listener Callback interface.
     */
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
