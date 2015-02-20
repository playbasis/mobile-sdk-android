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

    public static void sendEmail(@NonNull Playbasis playbasis, boolean isAsync,
                             @NonNull String playerId, @NonNull String subject, String message, String templateId,
                             final OnResult<List<String>>listener ){

        String endpoint = SDKUtil._EMAIL_API + "/send";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("subject", subject);
                if (message != null) jsonObject.put("message", message);
                if (templateId != null) jsonObject.put("template_id", templateId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {


            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            params.add(new BasicNameValuePair("subject", subject));
            if (message != null) params.add(new BasicNameValuePair("message", message));
            if (templateId != null) params.add(new BasicNameValuePair("template_id", templateId));

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

    public static void sendEmailCoupon(@NonNull Playbasis playbasis, boolean isAsync,
                                 @NonNull String playerId, @NonNull String refId, @NonNull String subject,
                                 String message, String templateId,
                                 final OnResult<List<String>>listener ){

        String endpoint = SDKUtil._EMAIL_API + "/goods";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("ref_id", refId);
                jsonObject.put("subject", subject);
                if (message != null) jsonObject.put("message", message);
                if (templateId != null) jsonObject.put("template_id", templateId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {
            String uri = SDKUtil.SERVER_URL + endpoint;

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
        

    }


    public static void sendSms(@NonNull Playbasis playbasis, boolean isAsync,
                                 @NonNull String playerId, String message, String templateId,
                                 final OnResult<List<String>>listener ){
        String endpoint = SDKUtil._SMS_API + "/send";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                if (message != null) jsonObject.put("message", message);
                if (templateId != null) jsonObject.put("template_id", templateId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {
            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            if (message != null) params.add(new BasicNameValuePair("message", message));
            if (templateId != null) params.add(new BasicNameValuePair("template_id", templateId));

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

    public static void sendSmsCoupon(@NonNull Playbasis playbasis, boolean isAsync,
                                       @NonNull String playerId, @NonNull String refId,
                                       String message, String templateId,
                                       final OnResult<List<String>>listener ){
        String endpoint = SDKUtil._SMS_API + "/goods";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("ref_id", refId);
                if (message != null) jsonObject.put("message", message);
                if (templateId != null) jsonObject.put("template_id", templateId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {
            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            params.add(new BasicNameValuePair("ref_id", refId));
            if (message != null) params.add(new BasicNameValuePair("message", message));
            if (templateId != null) params.add(new BasicNameValuePair("template_id", templateId));

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

    public static void sendPush(@NonNull Playbasis playbasis, boolean isAsync,
                                 @NonNull String playerId, String message, String templateId,
                                 final OnResult<List<String>>listener ){
        
        String endpoint = SDKUtil._PUSH_API + "/send";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                if (message != null) jsonObject.put("message", message);
                if (templateId != null) jsonObject.put("template_id", templateId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {
            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            if (message != null) params.add(new BasicNameValuePair("message", message));
            if (templateId != null) params.add(new BasicNameValuePair("template_id", templateId));

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

    public static void sendPushCoupon(@NonNull Playbasis playbasis, boolean isAsync,
                                       @NonNull String playerId, @NonNull String refId,
                                       String message, String templateId,
                                       final OnResult<List<String>>listener ){

        String endpoint = SDKUtil._PUSH_API + "/goods";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("ref_id", refId);
                if (message != null) jsonObject.put("message", message);
                if (templateId != null) jsonObject.put("template_id", templateId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Async.postData(playbasis, endpoint ,jsonObject , new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        }else {
            
            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            params.add(new BasicNameValuePair("ref_id", refId));
            if (message != null) params.add(new BasicNameValuePair("message", message));
            if (templateId != null) params.add(new BasicNameValuePair("template_id", templateId));

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
    
}
