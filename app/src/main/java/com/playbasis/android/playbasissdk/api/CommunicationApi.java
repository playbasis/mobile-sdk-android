package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.RedeemEvent;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class CommunicationApi  extends Api{
    public static final String TAG = "CommunicationApi";

    public static void sendEmail(@NonNull Playbasis playbasis, Boolean isAsync,
                             @NonNull String playerId, @NonNull String subject, String message, String templateId,
                             final OnResult<List<String>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._EMAIL_API + "/send";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("subject", subject));
        if(message!=null)params.add(new BasicNameValuePair("message", message));
        if(templateId!=null)params.add(new BasicNameValuePair("template_id", templateId));

        JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONArray result) {
                    List<String> events = JsonHelper.FromJsonArray(result, String.class);
                    if (listener != null) listener.onSuccess(events);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void sendEmailCoupon(@NonNull Playbasis playbasis, Boolean isAsync,
                                 @NonNull String playerId, @NonNull String refId, @NonNull String subject,
                                 String message, String templateId,
                                 final OnResult<List<String>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._EMAIL_API + "/goods";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("ref_id", refId));
        params.add(new BasicNameValuePair("subject", subject));
        if(message!=null)params.add(new BasicNameValuePair("message", message));
        if(templateId!=null)params.add(new BasicNameValuePair("template_id", templateId));

        JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONArray result) {
                List<String> events = JsonHelper.FromJsonArray(result, String.class);
                if (listener != null) listener.onSuccess(events);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }


    public static void sendSms(@NonNull Playbasis playbasis, Boolean isAsync,
                                 @NonNull String playerId, String message, String templateId,
                                 final OnResult<List<String>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._SMS_API + "/send";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        if(message!=null)params.add(new BasicNameValuePair("message", message));
        if(templateId!=null)params.add(new BasicNameValuePair("template_id", templateId));

        JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONArray result) {
                List<String> events = JsonHelper.FromJsonArray(result, String.class);
                if (listener != null) listener.onSuccess(events);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void sendSmsCoupon(@NonNull Playbasis playbasis, Boolean isAsync,
                                       @NonNull String playerId, @NonNull String refId,
                                       String message, String templateId,
                                       final OnResult<List<String>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._SMS_API + "/goods";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("ref_id", refId));
        if(message!=null)params.add(new BasicNameValuePair("message", message));
        if(templateId!=null)params.add(new BasicNameValuePair("template_id", templateId));

        JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONArray result) {
                List<String> events = JsonHelper.FromJsonArray(result, String.class);
                if (listener != null) listener.onSuccess(events);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void sendPush(@NonNull Playbasis playbasis, Boolean isAsync,
                                 @NonNull String playerId, String message, String templateId,
                                 final OnResult<List<String>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._PUSH_API + "/send";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        if(message!=null)params.add(new BasicNameValuePair("message", message));
        if(templateId!=null)params.add(new BasicNameValuePair("template_id", templateId));

        JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONArray result) {
                List<String> events = JsonHelper.FromJsonArray(result, String.class);
                if (listener != null) listener.onSuccess(events);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void sendPushCoupon(@NonNull Playbasis playbasis, Boolean isAsync,
                                       @NonNull String playerId, @NonNull String refId,
                                       String message, String templateId,
                                       final OnResult<List<String>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._EMAIL_API + "/goods";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("ref_id", refId));
        if(message!=null)params.add(new BasicNameValuePair("message", message));
        if(templateId!=null)params.add(new BasicNameValuePair("template_id", templateId));

        JsonArrayPOST(playbasis, uri, params, new OnResult<JSONArray>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONArray result) {
                List<String> events = JsonHelper.FromJsonArray(result, String.class);
                if (listener != null) listener.onSuccess(events);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }
    
}
