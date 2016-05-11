package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.RequestError;

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
    public static final String TEMPLATE_ID = "template_id";
    public static final String REF_ID = "ref_id";
    public static final String UPDATE_PLAYER_FAIL = "update player fail";
    public static final String SUBJECT = "subject";
    public static final String SEND = "send";

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
        PlayerValidatorApi.emailValidation(playbasis, playerId, new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    sendEmailToPlayer(playbasis, isAsync, playerId, subject, message, templateId, listener);
                } else {
                    if (listener != null)
                        listener.onError(new HttpError(new RequestError(UPDATE_PLAYER_FAIL,
                                RequestError.ERROR_CODE.DEFAULT)));
                }

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

        String endpoint = SDKUtil._EMAIL_API + SEND;

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.PLAYER_ID, playerId);
                jsonObject.put(SUBJECT, subject);
                if (message != null) jsonObject.put(ApiConst.MESSAGE, message);
                if (templateId != null) jsonObject.put(TEMPLATE_ID, templateId);
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


            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
            params.add(new BasicNameValuePair(SUBJECT, subject));
            if (message != null) params.add(new BasicNameValuePair(ApiConst.MESSAGE, message));
            if (templateId != null) params.add(new BasicNameValuePair(TEMPLATE_ID, templateId));

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

        PlayerValidatorApi.emailValidation(playbasis, playerId, new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    sendEmailCouponToPlayer(playbasis, isAsync, playerId, refId, subject, message, templateId, listener);
                } else {
                    if (listener != null)
                        listener.onError(new HttpError(new RequestError(UPDATE_PLAYER_FAIL,
                                RequestError.ERROR_CODE.DEFAULT)));
                }

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
        String endpoint = SDKUtil._EMAIL_API + ApiConst.GOODS;

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.PLAYER_ID, playerId);
                jsonObject.put(REF_ID, refId);
                jsonObject.put(SUBJECT, subject);
                if (message != null) jsonObject.put(ApiConst.MESSAGE, message);
                if (templateId != null) jsonObject.put(TEMPLATE_ID, templateId);
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
            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
            params.add(new BasicNameValuePair(REF_ID, refId));
            params.add(new BasicNameValuePair(SUBJECT, subject));
            if(message!=null)params.add(new BasicNameValuePair(ApiConst.MESSAGE, message));
            if(templateId!=null)params.add(new BasicNameValuePair(TEMPLATE_ID, templateId));

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
                if (result) {
                    sendSmsToPlayer(playbasis, isAsync, playerId, message, templateId, listener);
                } else {
                    if (listener != null)
                        listener.onError(new HttpError(new RequestError(UPDATE_PLAYER_FAIL,
                                RequestError.ERROR_CODE.DEFAULT)));
                }

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
        String endpoint = SDKUtil._SMS_API + SEND;

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.PLAYER_ID, playerId);
                if (message != null) jsonObject.put(ApiConst.MESSAGE, message);
                if (templateId != null) jsonObject.put(TEMPLATE_ID, templateId);
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
            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
            if (message != null) params.add(new BasicNameValuePair(ApiConst.MESSAGE, message));
            if (templateId != null) params.add(new BasicNameValuePair(TEMPLATE_ID, templateId));

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
                                       @NonNull final String playerId, @NonNull final String refId,
                                       final String message, final String templateId,
                                       final OnResult<List<String>>listener ){

        PlayerValidatorApi.smsValidation(playbasis, playerId, new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    sendSmsCouponToPlayer(playbasis, isAsync, playerId, refId, message, templateId, listener);
                } else {
                    if (listener != null)
                        listener.onError(new HttpError(new RequestError(UPDATE_PLAYER_FAIL,
                                RequestError.ERROR_CODE.DEFAULT)));
                }

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
        String endpoint = SDKUtil._SMS_API + ApiConst.GOODS;

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.PLAYER_ID, playerId);
                jsonObject.put(REF_ID, refId);
                if (message != null) jsonObject.put(ApiConst.MESSAGE, message);
                if (templateId != null) jsonObject.put(TEMPLATE_ID, templateId);
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
            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
            params.add(new BasicNameValuePair(REF_ID, refId));
            if (message != null) params.add(new BasicNameValuePair(ApiConst.MESSAGE, message));
            if (templateId != null) params.add(new BasicNameValuePair(TEMPLATE_ID, templateId));

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
    public static void deviceRegistration(@NonNull Playbasis playbasis, @NonNull String playerId, @NonNull String deviceToken,
                                          @NonNull String deviceDescription, @NonNull String deviceName, @NonNull String osType, final OnResult<String>listener) {

        deviceRegistration(playbasis, false, playerId, deviceToken, deviceDescription, deviceName, osType, listener);
    }

    public static void deviceRegistration(@NonNull Playbasis playbasis, boolean isAsync, @NonNull String playerId, @NonNull String deviceToken,
                                          @NonNull String deviceDescription, @NonNull String deviceName, @NonNull String osType, final OnResult<String>listener) {

        String endpoint = SDKUtil._PUSH_API + ApiConst.DEVICE_REGISTRATION +"/";

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.PLAYER_ID, playerId);
                jsonObject.put(ApiConst.DEVICE_TOKEN, deviceToken);
                jsonObject.put(ApiConst.DEVICE_DESCRIPTION, deviceDescription);
                jsonObject.put(ApiConst.DEVICE_NAME, deviceName);
                jsonObject.put(ApiConst.OS_TYPE, osType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            asyncPost(playbasis, endpoint, jsonObject, new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {

                }
            });
        } else {
            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
            params.add(new BasicNameValuePair(ApiConst.DEVICE_TOKEN, deviceToken));
            params.add(new BasicNameValuePair(ApiConst.DEVICE_DESCRIPTION, deviceDescription));
            params.add(new BasicNameValuePair(ApiConst.DEVICE_NAME, deviceName));
            params.add(new BasicNameValuePair(ApiConst.OS_TYPE, osType));

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    if (listener != null) listener.onSuccess("OK");
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
        
        String endpoint = SDKUtil._PUSH_API + SEND;

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.PLAYER_ID, playerId);
                if (message != null) jsonObject.put(ApiConst.MESSAGE, message);
                if (templateId != null) jsonObject.put(TEMPLATE_ID, templateId);
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
            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
            if (message != null) params.add(new BasicNameValuePair(ApiConst.MESSAGE, message));
            if (templateId != null) params.add(new BasicNameValuePair(TEMPLATE_ID, templateId));

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

        String endpoint = SDKUtil._PUSH_API + ApiConst.GOODS;

        if(isAsync){

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put(ApiConst.PLAYER_ID, playerId);
                jsonObject.put(REF_ID, refId);
                if (message != null) jsonObject.put(ApiConst.MESSAGE, message);
                if (templateId != null) jsonObject.put(TEMPLATE_ID, templateId);
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
            
            String uri = playbasis.getUrl() + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(ApiConst.PLAYER_ID, playerId));
            params.add(new BasicNameValuePair(REF_ID, refId));
            if (message != null) params.add(new BasicNameValuePair(ApiConst.MESSAGE, message));
            if (templateId != null) params.add(new BasicNameValuePair(TEMPLATE_ID, templateId));

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
