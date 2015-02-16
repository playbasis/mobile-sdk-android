package com.playbasis.android.playbasissdk.api;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.HttpsTrustManager;
import com.playbasis.android.playbasissdk.http.PlayBasisLog;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.Response;
import com.playbasis.android.playbasissdk.http.toolbox.JSONObjectRequest;
import com.playbasis.android.playbasissdk.http.toolbox.ParamsHelper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public abstract class Api {
    public static final String TAG = "Api";


    protected static void JsonObjectGET(final Playbasis playbasis, String uri, List<NameValuePair> params,
                                 final OnResult<JSONObject>
            listener) {
        HttpsTrustManager.allowAllSSL();
        
        //Add params to the request
        if(params==null) params = new ArrayList<>();
        params.add(new BasicNameValuePair("api_key", playbasis.getKeyStore().getApiKey()));
        

        JSONObjectRequest jsonObjReq = new JSONObjectRequest(Request.Method.GET,
                ParamsHelper.addParams(uri, params), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        PlayBasisLog.v(TAG, response.toString());
                        if (listener != null) listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(HttpError error) {
                PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                if (listener != null) listener.onError(error);
            }
        });
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonObjReq);
    }


}
