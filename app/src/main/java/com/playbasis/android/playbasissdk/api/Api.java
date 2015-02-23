package com.playbasis.android.playbasissdk.api;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.HttpsTrustManager;
import com.playbasis.android.playbasissdk.http.PlayBasisLog;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.Response;
import com.playbasis.android.playbasissdk.http.toolbox.JSONArrayRequest;
import com.playbasis.android.playbasissdk.http.toolbox.JSONObjectRequest;
import com.playbasis.android.playbasissdk.http.toolbox.ParamsHelper;
import com.playbasis.android.playbasissdk.http.toolbox.StringRequest;
import com.playbasis.android.playbasissdk.secure.RequestStorage;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
                        PlayBasisLog.v(TAG, response!=null? response.toString() : "Response is null");
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

    protected static void JsonObjectPOST(final Playbasis playbasis, String uri, final List<NameValuePair> httpParams,
                                        final OnResult<JSONObject> listener) {
        if (!playbasis.isNetworkAvailable()){
            RequestStorage storage = new RequestStorage(playbasis.getContext());
            storage.saveRequest(playbasis, uri , httpParams);
        }

        HttpsTrustManager.allowAllSSL();
        final JSONObjectRequest jsonObjReq = new JSONObjectRequest(Request.Method.POST,
                uri, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        PlayBasisLog.v(TAG, response!=null? response.toString() : "Response is null");
                        if (listener != null) listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(HttpError error) {
                PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                if (listener != null) listener.onError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<>();
                params.put("api_key", playbasis.getKeyStore().getApiKey());
                params.put("token", playbasis.getAuthenticator().getToken());
                if(httpParams!=null){
                    for (NameValuePair pair : httpParams){
                        params.put(pair.getName(), pair.getValue());
                    }
                }
                return params;
            }
        };
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonObjReq);
    }

    protected static void JsonArrayGET(final Playbasis playbasis, String uri, List<NameValuePair> params,
                                         final OnResult<JSONArray>
                                                 listener) {
        HttpsTrustManager.allowAllSSL();

        //Add params to the request
        if(params==null) params = new ArrayList<>();
        params.add(new BasicNameValuePair("api_key", playbasis.getKeyStore().getApiKey()));

        final JSONArrayRequest jsonObjReq = new JSONArrayRequest(Request.Method.GET,
                ParamsHelper.addParams(uri, params), null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
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

    protected static void JsonArrayPOST(final Playbasis playbasis, String uri, final List<NameValuePair> httpParams,
                                         final OnResult<JSONArray>
                                                 listener) {
        HttpsTrustManager.allowAllSSL();
        final JSONArrayRequest jsonArrReq = new JSONArrayRequest(Request.Method.POST,
                uri, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        PlayBasisLog.v(TAG, response!=null? response.toString() : "Response is null");
                        if (listener != null) listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(HttpError error) {
                PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                if (listener != null) listener.onError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<>();
                params.put("api_key", playbasis.getKeyStore().getApiKey());
                params.put("token", playbasis.getAuthenticator().getToken());
                if(httpParams!=null){
                    for (NameValuePair pair : httpParams){
                        params.put(pair.getName(), pair.getValue());
                    }
                }
                return params;
            }
        };
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonArrReq);
    }

    protected static void StringGET(final Playbasis playbasis, String uri, List<NameValuePair> params,
                                        final OnResult<String>
                                                listener) {
        HttpsTrustManager.allowAllSSL();
        //Add params to the request
        if(params==null) params = new ArrayList<>();
        params.add(new BasicNameValuePair("api_key", playbasis.getKeyStore().getApiKey()));

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ParamsHelper.addParams(uri, params), new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        PlayBasisLog.v(TAG, response!=null? response : "Response is null");
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
        playbasis.getHttpManager().addToRequestQueue(stringRequest);
    }

}
