package com.playbasis.android.playbasissdk.http.toolbox;

import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.NetworkResponse;
import com.playbasis.android.playbasissdk.http.ParseError;
import com.playbasis.android.playbasissdk.http.PlaybasisResponse;
import com.playbasis.android.playbasissdk.http.Request;
import com.playbasis.android.playbasissdk.http.Response;
import com.playbasis.android.playbasissdk.http.ServerError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class JSONObjectRequest  extends JSONRequest<JSONObject> {
    public static final String TAG = "JSONObjectRequest";


    public JSONObjectRequest(String url, Map<String, String> params,
                         Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(Method.POST, url, params, reponseListener ,errorListener);
    }

    public JSONObjectRequest(int method, String url, Map<String, String> params,
                         Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, params, reponseListener ,errorListener);
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            PlaybasisResponse playbasisResponse = new PlaybasisResponse(new JSONObject(jsonString));
            if(playbasisResponse.success){
                return Response.success(playbasisResponse.response, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.error(new HttpError(playbasisResponse));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }

    }


}
