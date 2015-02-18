package com.playbasis.android.playbasissdk.http.toolbox;

import com.google.gson.JsonArray;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.NetworkResponse;
import com.playbasis.android.playbasissdk.http.ParseError;
import com.playbasis.android.playbasissdk.http.PlaybasisResponse;
import com.playbasis.android.playbasissdk.http.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by gregoire barret on 2/17/15.
 * For PlayBasisSdk project.
 */
public class JSONArrayRequest extends JSONRequest<JSONArray> {
    public static final String TAG = "JSONArrayRequest";


    public JSONArrayRequest(String url, Map<String, String> params,
                             Response.Listener<JSONArray> reponseListener, Response.ErrorListener errorListener) {
        super(Method.POST, url, params, reponseListener ,errorListener);
    }

    public JSONArrayRequest(int method, String url, Map<String, String> params,
                             Response.Listener<JSONArray> reponseListener, Response.ErrorListener errorListener) {
        super(method, url, params, reponseListener ,errorListener);
    }


    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            PlaybasisResponse playbasisResponse = new PlaybasisResponse(new JSONObject(jsonString));
            if(playbasisResponse.success){
                return Response.success((JSONArray)playbasisResponse.response, HttpHeaderParser.parseCacheHeaders(response));
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
