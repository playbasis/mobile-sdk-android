package com.playbasis.android.playbasissdk.http.toolbox;

import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.NetworkResponse;
import com.playbasis.android.playbasissdk.http.PlayBasisLog;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.Response;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by gregoire barret on 2/17/15.
 * For PlayBasisSdk project.
 * 
 * Same as {@link com.playbasis.android.playbasissdk.http.toolbox.JsonRequest} but get the params 
 */
public abstract class JSONRequest<T> extends Request<T> {
    public static final String TAG = "JSONRequest";

    private Map<String, String> params;

    private final Response.Listener<T> mListener;

    public JSONRequest(int method, String url, Map<String, String> params,
                             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.params = params;
    }


    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    abstract protected Response<T> parseNetworkResponse(NetworkResponse response);

    protected Map<String, String> getParams()
            throws AuthFailureError {
        return params;
    };




}
