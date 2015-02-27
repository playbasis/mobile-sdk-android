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

    /**
     * Send email to a player.
     * @param playbasis Playbasis object.
     * @param playerId Player id as used in client's website.
     * @param subject Email subject.
     * @param message Email message (either message or template_id is required).
     * @param templateId Template message (either message or template_id is required).
     * @param listener Callback interface.
     */
    public static void sendEmail(@NonNull Playbasis playbasis,
                                 @NonNull String playerId, @NonNull String subject, String message, String templateId,
                                 final OnResult<List<String>>listener ){
        sendEmail(playbasis, false, playerId, subject, message, templateId, listener);
    }
    
    /**
     *  {@link #sendEmail(com.playbasis.android.playbasissdk.core.Playbasis, String, String, String, String, OnResult)}
     * @param playbasis  Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId Player id as used in client's website.
     * @param subject Email subject.
     * @param message Email message (either message or template_id is required).
     * @param templateId Template message (either message or template_id is required).
     * @param listener Callback interface.
     */
    public static void sendEmail(@NonNull final Playbasis playbasis, final boolean isAsync,
                             @NonNull final String playerId, @NonNull final String subject, final String message, final String templateId,
                             final OnResult<List<String>>listener ){
        PlayerValidatorApi.emailValidation(playbasis,playerId,new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                sendEmailToPlayer(playbasis, isAsync, playerId, subject, message, templateId, listener);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });

    }

    private static void sendEmailToPlayer(@NonNull Playbasis playbasis, boolean isAsync,
                                 @NonNull String playerId, @NonNull String subject, String message, String templateId,
                                 final OnResult<List<String>>listener ){

        String endpoint = SDKUtil._EMAIL_API + "send";

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
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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
    

    /**
     * Send coupon to a player via email.
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param refId Reference transaction id for redemption.
     * @param subject Email subject.
     * @param message Email message (either message or template_id is required).
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendEmailCoupon(@NonNull Playbasis playbasis,
                                       @NonNull String playerId, @NonNull String refId, @NonNull String subject,
                                       String message, String templateId,
                                       final OnResult<List<String>>listener ){
        sendEmailCoupon(playbasis, false, playerId, refId,subject,message,templateId,listener);
    }

    /**
     * {@link #sendEmailCoupon(com.playbasis.android.playbasissdk.core.Playbasis, String, String, String, String, String, OnResult)} 
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId  Player id as used in client's website.
     * @param refId Reference transaction id for redemption.
     * @param subject Email subject.
     * @param message Email message (either message or template_id is required).
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendEmailCoupon(@NonNull final Playbasis playbasis, final boolean isAsync,
                                 @NonNull final String playerId, @NonNull final String refId, @NonNull final String subject,
                                 final String message, final String templateId,
                                 final OnResult<List<String>>listener ){

        PlayerValidatorApi.emailValidation(playbasis,playerId,new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                sendEmailCouponToPlayer(playbasis, isAsync, playerId, refId, subject, message, templateId, listener);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
        

    }


    private static void sendEmailCouponToPlayer(@NonNull Playbasis playbasis, boolean isAsync,
                                       @NonNull String playerId, @NonNull String refId, @NonNull String subject,
                                       String message, String templateId,
                                       final OnResult<List<String>>listener ){
        String endpoint = SDKUtil._EMAIL_API + "goods";

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
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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

    /**
     * Send SMS to a player.
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendSms(@NonNull Playbasis playbasis,
                               @NonNull String playerId, String message, String templateId,
                               final OnResult<List<String>>listener ){
        sendSms(playbasis, false, playerId, message,templateId,listener);
    }

    /**
     * {@link #sendSms(com.playbasis.android.playbasissdk.core.Playbasis, String, String, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId  Player id as used in client's website.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendSms(@NonNull final Playbasis playbasis, final boolean isAsync,
                                 @NonNull final String playerId, final String message, final String templateId,
                                 final OnResult<List<String>>listener ){

        PlayerValidatorApi.smsValidation(playbasis, playerId, new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                sendSmsToPlayer(playbasis, isAsync, playerId, message, templateId, listener);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }


    private static void sendSmsToPlayer(@NonNull Playbasis playbasis, boolean isAsync,
                               @NonNull String playerId, String message, String templateId,
                               final OnResult<List<String>>listener ){
        String endpoint = SDKUtil._SMS_API + "send";

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
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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

    /**
     * Send coupon to a player via SMS.
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param refId Reference transaction id for redemption.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendSmsCoupon(@NonNull Playbasis playbasis,
                                     @NonNull String playerId, @NonNull String refId,
                                     String message, String templateId,
                                     final OnResult<List<String>>listener ){
        sendSmsCoupon(playbasis,false,playerId,refId,message,templateId,listener);
        
    }

    /**
     * {@link #sendEmailCoupon(com.playbasis.android.playbasissdk.core.Playbasis, String, String, String, String, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId  Player id as used in client's website.
     * @param refId Reference transaction id for redemption.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendSmsCoupon(@NonNull final Playbasis playbasis, final boolean isAsync,
                                       @NonNull final String playerId, @NonNull String refId,
                                       final String message, final String templateId,
                                       final OnResult<List<String>>listener ){

        PlayerValidatorApi.smsValidation(playbasis,playerId,new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                sendSmsToPlayer(playbasis, isAsync, playerId, message, templateId, listener);
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
        
    }


    private static void sendSmsCouponToPlayer(@NonNull Playbasis playbasis, boolean isAsync,
                                     @NonNull String playerId, @NonNull String refId,
                                     String message, String templateId,
                                     final OnResult<List<String>>listener ){
        String endpoint = SDKUtil._SMS_API + "goods";

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
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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

    /**
     * Send push notification to a player.
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendPush(@NonNull Playbasis playbasis,
                                @NonNull String playerId, String message, String templateId,
                                final OnResult<List<String>>listener ){
        sendPush(playbasis,false,playerId,message,templateId, listener);
    }
    /**
     * {@link #sendPush(com.playbasis.android.playbasissdk.core.Playbasis, String, String, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId  Player id as used in client's website.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendPush(@NonNull Playbasis playbasis, boolean isAsync,
                                 @NonNull String playerId, String message, String templateId,
                                 final OnResult<List<String>>listener ){
        
        String endpoint = SDKUtil._PUSH_API + "send";

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
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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

    /**
     * Send coupon to a player via push notification.
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param refId Reference transaction id for redemption.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendPushCoupon(@NonNull Playbasis playbasis, 
                                      @NonNull String playerId, @NonNull String refId,
                                      String message, String templateId,
                                      final OnResult<List<String>>listener ){
        sendPushCoupon(playbasis,false,playerId,refId,message,templateId,listener);
    }
    /**
     * {@link #sendPushCoupon(com.playbasis.android.playbasissdk.core.Playbasis, String, String, String, String, OnResult)}
     * @param playbasis Playbasis object.
     * @param isAsync Make the request async.
     * @param playerId  Player id as used in client's website.
     * @param refId Reference transaction id for redemption.
     * @param message SMS message, can use variable {{code}} for the actual code.
     * @param templateId Template message (either message or template_id is required).
     * @param listener  Callback interface.
     */
    public static void sendPushCoupon(@NonNull Playbasis playbasis, boolean isAsync,
                                       @NonNull String playerId, @NonNull String refId,
                                       String message, String templateId,
                                       final OnResult<List<String>>listener ){

        String endpoint = SDKUtil._PUSH_API + "goods";

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
            asyncPost(playbasis, endpoint ,jsonObject , new OnResult<String>() {
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
