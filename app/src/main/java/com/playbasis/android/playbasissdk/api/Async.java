package com.playbasis.android.playbasissdk.api;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.HttpsTrustManager;
import com.playbasis.android.playbasissdk.http.PlayBasisLog;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.Response;
import com.playbasis.android.playbasissdk.http.toolbox.JSONObjectRequest;
import com.playbasis.android.playbasissdk.http.toolbox.JsonObjectRequest;
import com.playbasis.android.playbasissdk.http.toolbox.ParamsHelper;
import com.playbasis.android.playbasissdk.http.toolbox.StringJSONBodyRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Async extends Api {
    public static final String TAG = "Async";

    public static void postData(final Playbasis playbasis, String endpoint, JSONObject jsonObject,
                                final OnResult<String>
                                        listener) {
        HttpsTrustManager.allowAllSSL();

        JSONObject params = new JSONObject();
        try {
            params.put("endpoint", endpoint);
            params.put("data", jsonObject);
            params.putOpt("channel", playbasis.getChannel());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringJSONBodyRequest jsonBodyRequest = new StringJSONBodyRequest(Request.Method.POST,
                SDKUtil.SERVER_URL_ASYNC, params,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        PlayBasisLog.v(TAG, response != null ? response : "Response is null");
                        if (listener != null) listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(HttpError error) {
                        PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                        if (listener != null) listener.onError(error);
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonBodyRequest);


    }
}
