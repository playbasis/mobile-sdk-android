package com.playbasis.android.playbasissdk.http.toolbox;

import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.NetworkResponse;
import com.playbasis.android.playbasissdk.http.ParseError;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.RequestError;
import com.playbasis.android.playbasissdk.http.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class StringJSONBodyRequest extends JsonRequest<String> {
    public static final String TAG = "StringJSONBodyRequest";

    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param jsonRequest   A {@link org.json.JSONObject} to post with the request. Null is allowed and
     *                      indicates no parameters will be posted along with request.
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public StringJSONBodyRequest(int method, String url, JSONObject jsonRequest,
                                 Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                errorListener);
    }

    /**
     * Constructor which defaults to <code>GET</code> if <code>jsonRequest</code> is
     * <code>null</code>, <code>POST</code> otherwise.
     *
     * @see #StringJSONBodyRequest(int, String, org.json.JSONObject, com.playbasis.android.playbasissdk.http.Response.Listener, com.playbasis.android.playbasissdk.http.Response.ErrorListener)
     */
    public StringJSONBodyRequest(String url, JSONObject jsonRequest, Response.Listener<String> listener,
                                 Response.ErrorListener errorListener) {
        this(jsonRequest == null ? Method.GET : Method.POST, url, jsonRequest,
                listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String string = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            if (string.toLowerCase().equals("ok")) {
                return Response.success(string, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.error(new HttpError(new RequestError(string, RequestError.ERROR_CODE.DEFAULT)));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

}
