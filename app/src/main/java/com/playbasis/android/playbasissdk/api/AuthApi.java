package com.playbasis.android.playbasissdk.api;

import android.util.Log;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.HttpsTrustManager;
import com.playbasis.android.playbasissdk.http.PlayBasisLog;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.Response;
import com.playbasis.android.playbasissdk.http.toolbox.JSONObjectRequest;
import com.playbasis.android.playbasissdk.http.toolbox.JsonObjectRequest;
import com.playbasis.android.playbasissdk.http.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class AuthApi extends Api {
    public static final String TAG = "AthApi";

    /**
     * Request the token to the backend. 
     * @param playbasis Playbasis object.
     * @param listener OnResult listener.
     */
    public static void auth(final Playbasis playbasis, final OnResult<Token> listener) {
        String uri = playbasis.getServerUrl()+ "/Auth";
        request(playbasis, uri, listener);
    }


    /**
     * Request a new token to the backend.
     * @param playbasis Playbasis object.
     * @param listener OnResult listener.
     */
    public static void authRenew(final Playbasis playbasis, final OnResult<Token> listener){
        String uri = playbasis.getServerUrl()+ "/Auth/renew";
        request(playbasis, uri, listener);
    }
    
    private static void request(final Playbasis playbasis, final String uri, final OnResult<Token> listener){
        HttpsTrustManager.allowAllSSL();
        JSONObjectRequest jsonObjReq = new JSONObjectRequest(Request.Method.POST,
                uri, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        PlayBasisLog.v(TAG, response.toString());
                        try {
                            Token token = new Token(response);
                            if (listener != null) listener.onSuccess(token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (listener != null) listener.onError(new HttpError(e));
                        }

                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(HttpError error) {
                PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                if (listener != null) listener.onError(error);
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("api_key", playbasis.getKeyStore().getApiKey());
                params.put("api_secret", playbasis.getKeyStore().getApiSecret());
                return params;
            }
        };
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonObjReq);
    }

}