package com.playbasis.android.playbasissdk.api;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.ApiHelper;
import com.playbasis.android.playbasissdk.helper.DateHelper;
import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.HttpsTrustManager;
import com.playbasis.android.playbasissdk.http.PlayBasisLog;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.RequestError;
import com.playbasis.android.playbasissdk.http.Response;
import com.playbasis.android.playbasissdk.http.toolbox.JSONArrayRequest;
import com.playbasis.android.playbasissdk.http.toolbox.JSONObjectRequest;
import com.playbasis.android.playbasissdk.http.toolbox.ParamsHelper;
import com.playbasis.android.playbasissdk.http.toolbox.StringJSONBodyRequest;
import com.playbasis.android.playbasissdk.http.toolbox.StringRequest;
import com.playbasis.android.playbasissdk.model.StoredRequest;
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

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public abstract class Api {
    public static final String TAG = "Api";
    private static Boolean isResendRunning = false;
    
    private static int renewCount = 0;


    /**
     * JsonObject GET super method.
     *
     * @param playbasis Playbasic objecct.
     * @param uri       URL of the request.
     * @param params    Params of the request.
     * @param listener  JSONObject Callback listener.
     */
    protected static void JsonObjectGET(final Playbasis playbasis, final String uri, List<NameValuePair> params,
                                        final OnResult<JSONObject> listener) {
        if (!playbasis.isNetworkAvailable()) {
            if (listener != null) listener.onError(new HttpError(RequestError.NoNetwork()));
            return;
        } else resendRequests(playbasis);
        HttpsTrustManager.allowAllSSL();

        //Add params to the request
        if (params == null) params = new ArrayList<>();
        params.add(new BasicNameValuePair("api_key", playbasis.getKeyStore().getApiKey()));

        JSONObjectRequest jsonObjReq = new JSONObjectRequest(Request.Method.GET,
                ParamsHelper.addParams(uri, params), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        PlayBasisLog.v(TAG, response != null ? response.toString() : "Response is null");
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiHelper.getHeaderMap(playbasis);
            }
        };
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonObjReq);
    }

    /**
     * JsonObject POST super method.
     *
     * @param playbasis  Playbasic objecct.
     * @param uri        URL of the request.
     * @param httpParams Params of the request.
     * @param listener   JSONObject Callback listener.
     */
    protected static void JsonObjectPOST(final Playbasis playbasis, final String uri,
                                         final List<NameValuePair> httpParams,
                                         final OnResult<JSONObject> listener) {
        JsonObjectPOST(playbasis, uri, null, httpParams, false, listener);
    }

    protected static void JsonObjectPOST(final Playbasis playbasis, final String uri,
                                         final Long timestamp,
                                         final List<NameValuePair> httpParams,
                                         final OnResult<JSONObject> listener) {
        JsonObjectPOST(playbasis, uri, timestamp, httpParams, false, listener);
    }

    protected static void JsonObjectPOST(final Playbasis playbasis, final String uri,
                                         final List<NameValuePair> httpParams,
                                         final boolean enabledOffline,
                                         final OnResult<JSONObject> listener) {
        JsonObjectPOST(playbasis, uri, null, httpParams, enabledOffline, listener);
    }

    public static void JsonObjectPOST(final Playbasis playbasis, final String uri,
                                         final Long timestamp,
                                         final List<NameValuePair> httpParams,
                                         final boolean enabledOffline,
                                         final OnResult<JSONObject> listener) {
        if (!playbasis.isNetworkAvailable()) {
            if (enabledOffline) {
                RequestStorage storage = new RequestStorage(playbasis.getContext());
                storage.save(playbasis, uri, httpParams, "object");
            }
            if (listener != null) listener.onError(new HttpError(RequestError.NoNetwork()));
            return;
        } else resendRequests(playbasis);

        HttpsTrustManager.allowAllSSL();
        final JSONObjectRequest jsonObjReq = new JSONObjectRequest(Request.Method.POST,
                uri, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        renewCount = 0;
                        PlayBasisLog.v(TAG, response != null ? response.toString() : "Response is null");
                        if (listener != null) listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(HttpError error) {
                //Request a new token and resend the request if token is invalid.
                if (error.requestError!=null && (error.requestError.errorCode == RequestError.ERROR_CODE.INVALID_TOKEN || error.requestError.errorCode == RequestError.ERROR_CODE.TOKEN_REQUIRED)
                 && renewCount < 3       ) {
                    AuthAuthenticator authAuthenticator = new AuthAuthenticator(playbasis);
                    authAuthenticator.requestRenewAuthToken(new OnResult<AuthToken>() {
                        @Override
                        public void onSuccess(AuthToken result) {
                            renewCount++;
                            JsonObjectPOST(playbasis, uri, timestamp, httpParams, enabledOffline, listener);
                        }

                        @Override
                        public void onError(HttpError error) {
                            renewCount = 0;
                            PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                            if (listener != null) listener.onError(error);
                        }
                    });
                } else {
                    renewCount = 0;
                    PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                    if (listener != null) listener.onError(error);
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<>();
                params.put("api_key", playbasis.getKeyStore().getApiKey());
                params.put("token", playbasis.getAuthenticator().getToken());
                if (httpParams != null) {
                    for (NameValuePair pair : httpParams) {
                        params.put(pair.getName(), pair.getValue());
                    }
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = ApiHelper.getHeaderMap(playbasis);
                if (timestamp != null)
                    headers.put("Date", DateHelper.timestampToHTPPDate(timestamp));
                else
                    headers.put("Date", DateHelper.timestampToHTPPDate(DateHelper.currentTimetamp()));
                return headers;
            }
        };
        //Remove the cache
        jsonObjReq.setShouldCache(false);
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonObjReq);
    }

    /**
     * JsonArray GET super method.
     *
     * @param playbasis Playbasic objecct.
     * @param uri       URL of the request.
     * @param params    Params of the request.
     * @param listener  JSONArray Callback listener.
     */
    protected static void JsonArrayGET(final Playbasis playbasis, String uri, List<NameValuePair> params,
                                       final OnResult<JSONArray>
                                               listener) {
        if (!playbasis.isNetworkAvailable()) {
            if (listener != null) listener.onError(new HttpError(RequestError.NoNetwork()));
            return;
        } else resendRequests(playbasis);
        HttpsTrustManager.allowAllSSL();

        //Add params to the request
        if (params == null) params = new ArrayList<>();
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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiHelper.getHeaderMap(playbasis);
            }
        };
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonObjReq);
    }

    /**
     * JsonArray POST super method.
     *
     * @param playbasis  Playbasic objecct.
     * @param uri        URL of the request.
     * @param httpParams Params of the request.
     * @param  enabledOffline Enable offline mode or not.
     * @param listener   JSONArray Callback listener.
     */
    protected static void JsonArrayPOST(final Playbasis playbasis, final String uri,
                                        final List<NameValuePair> httpParams,
                                        final boolean enabledOffline,
                                        final OnResult<JSONArray>
                                                listener) {

        if (!playbasis.isNetworkAvailable()) {
            if (enabledOffline) {
                RequestStorage storage = new RequestStorage(playbasis.getContext());
                storage.save(playbasis, uri, httpParams, "array");
            }
            if (listener != null) listener.onError(new HttpError(RequestError.NoNetwork()));
            return;
        } else resendRequests(playbasis);

        HttpsTrustManager.allowAllSSL();
        final JSONArrayRequest jsonArrReq = new JSONArrayRequest(Request.Method.POST,
                uri, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        PlayBasisLog.v(TAG, response != null ? response.toString() : "Response is null");
                        if (listener != null) listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(HttpError error) {
                //Request a new token and resend the request if token is invalid.
                if (error.requestError!=null && error.requestError.errorCode == RequestError.ERROR_CODE.INVALID_TOKEN) {
                    AuthAuthenticator authAuthenticator = new AuthAuthenticator(playbasis);
                    authAuthenticator.requestRenewAuthToken(new OnResult<AuthToken>() {
                        @Override
                        public void onSuccess(AuthToken result) {
                            JsonArrayPOST(playbasis, uri, httpParams, enabledOffline, listener);
                        }

                        @Override
                        public void onError(HttpError error) {
                            PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                            if (listener != null) listener.onError(error);
                        }
                    });
                } else {
                    PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                    if (listener != null) listener.onError(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<>();
                params.put("api_key", playbasis.getKeyStore().getApiKey());
                params.put("token", playbasis.getAuthenticator().getToken());
                if (httpParams != null) {
                    for (NameValuePair pair : httpParams) {
                        params.put(pair.getName(), pair.getValue());
                    }
                }
                return params;
            }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiHelper.getHeaderMap(playbasis);
            }

        };
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonArrReq);
    }

    protected static void JsonArrayPOST(final Playbasis playbasis, final String uri,
                                        final List<NameValuePair> httpParams,
                                        final OnResult<JSONArray>
                                                listener) {
        JsonArrayPOST(playbasis, uri, httpParams, false, listener);
    }

    /**
     * String GET super method.
     *
     * @param playbasis Playbasic objecct.
     * @param uri       URL of the request.
     * @param params    Params of the request.
     * @param listener  String Callback listener.
     */
    protected static void StringGET(final Playbasis playbasis, String uri, List<NameValuePair> params,
                                    final OnResult<String>
                                            listener) {
        resendRequests(playbasis);
        HttpsTrustManager.allowAllSSL();
        //Add params to the request
        if (params == null) params = new ArrayList<>();
        params.add(new BasicNameValuePair("api_key", playbasis.getKeyStore().getApiKey()));

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ParamsHelper.addParams(uri, params), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                PlayBasisLog.v(TAG, response != null ? response : "Response is null");
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                return ApiHelper.getHeaderMap(playbasis);
            }
        };

        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(stringRequest);
    }

    /**
     * Async super method.
     *
     * @param playbasis  Playbasic objecct.
     * @param endpoint   endpoint of the request.
     * @param jsonObject Json data.
     * @param listener   String Callback listener.
     */
    protected static void asyncPost(final Playbasis playbasis, String endpoint,
                                    JSONObject jsonObject, final OnResult<String> listener) {
        asyncPost(playbasis, endpoint, null, jsonObject, listener);
    }

    /**
     * Async super method.
     *
     * @param playbasis  Playbasic objecct.
     * @param endpoint   endpoint of the request.
     * @param timestamp  timestamp of the request.
     * @param jsonObject Json data.
     * @param listener   String Callback listener.
     */
    protected static void asyncPost(final Playbasis playbasis, final String endpoint, final Long timestamp,
                                    final JSONObject jsonObject, final OnResult<String> listener) {

        JSONObject params = new JSONObject();
        try {
            params.put("endpoint", endpoint);
            params.put("data", jsonObject);
            params.putOpt("channel", playbasis.getChannel());
            params.put("timestamp", timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (!playbasis.isNetworkAvailable()) {
            RequestStorage storage = new RequestStorage(playbasis.getContext());
            storage.save(playbasis, endpoint, jsonObject, timestamp);
            if (listener != null) listener.onError(new HttpError(RequestError.NoNetwork()));
            return;
        } else resendRequests(playbasis);

        HttpsTrustManager.allowAllSSL();

        String url = "";
        if(playbasis.getUrl() != null) {
            url = url + playbasis.getUrl();
        } else {
            url = url + SDKUtil.SERVER_URL;
        }
        url = url + SDKUtil.SERVER_URL_ASYNC;

        StringJSONBodyRequest jsonBodyRequest = new StringJSONBodyRequest(Request.Method.POST,
                url, params,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        renewCount = 0;
                        PlayBasisLog.v(TAG, response != null ? response : "Response is null");
                        if (listener != null) listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(HttpError error) {
                        //Request a new token and resend the request if token is invalid.
                        if (error.requestError!=null && error.requestError.errorCode == RequestError.ERROR_CODE
                                .INVALID_TOKEN && renewCount < 3) {
                            AuthAuthenticator authAuthenticator = new AuthAuthenticator(playbasis);
                            authAuthenticator.requestRenewAuthToken(new OnResult<AuthToken>() {
                                @Override
                                public void onSuccess(AuthToken result) {
                                    renewCount++;
                                    asyncPost(playbasis, endpoint, timestamp, jsonObject, listener);
                                }

                                @Override
                                public void onError(HttpError error) {
                                    renewCount = 0;
                                    PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                                    if (listener != null) listener.onError(error);
                                }
                            });
                        } else {
                            renewCount = 0;
                            PlayBasisLog.e(TAG, "Error: " + error.getMessage());
                            if (listener != null) listener.onError(error);
                        }
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = ApiHelper.getHeaderMap(playbasis);
                headers.put("Content-Type", "application/json");
                if (timestamp != null)
                    headers.put("Date", DateHelper.timestampToHTPPDate(timestamp));
                return headers;
            }
        };
        //Remove the cache
        jsonBodyRequest.setShouldCache(false);
        // Adding request to request queue
        playbasis.getHttpManager().addToRequestQueue(jsonBodyRequest);
    }

    public static void resendRequests(final Playbasis playbasis) {
        if (!playbasis.isNetworkAvailable() || isResendRunning) return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                isResendRunning = true;
                RequestStorage storage = new RequestStorage(playbasis.getContext());
                List<StoredRequest> requests = storage.loadAll();
                if (requests != null && requests.size() > 0) {
                    for (StoredRequest request : requests) {
/*                        if (request != null) asyncPost(playbasis, request.getUrl(), request.getTimestamp(),
                                request.paramsToJson(),
                                null);*/
                        if (request !=null) {
                            if (request.getMode() != null && request.getMode()  == "array") {
                                JsonArrayPOST(playbasis, playbasis.getUrl() + request.getUrl(), request.paramsToValuePair(), true, null);
                            } else {
                                //Default is Object
                                JsonObjectPOST(playbasis, playbasis.getUrl() + request.getUrl(), request.getTimestamp(), request.paramsToValuePair(), true, null);
                            }
                        }
                    }
                }
                isResendRunning = false;
            }
        }).start();
    }
}
