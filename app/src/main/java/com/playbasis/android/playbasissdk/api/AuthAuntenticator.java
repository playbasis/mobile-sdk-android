package com.playbasis.android.playbasissdk.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.toolbox.Authenticator;
import com.playbasis.android.playbasissdk.secure.PrivatePreferences;
import com.playbasis.android.playbasissdk.secure.SecurePreferences;

import java.util.Date;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class AuthAuntenticator {
    public static final String TAG = "AuthAuntenticator";


    /**
     * Context of the application. 
     */
    private final Context mContext;

    /**
     * Private preferences. 
     */
    private final PrivatePreferences mPrefs;

    /**
     * Token of the current session.
     * */
    public Token token;

    /**
     * init context and secure shared pref. 
     * @param context
     */
    public AuthAuntenticator(Context context) {
        this.mContext = context;
        this.mPrefs = new PrivatePreferences(mContext);
    }

    /**
     *  Synchronously get the saved token. If no token already init, return null.
     * @return token
     */
    @Nullable public String getAuthToken() {
        //Try get token saved on shared preferences.
        if(token==null) { token = mPrefs.getToken(); }
        return token == null ? null : token.getToken();
    }

    /**
     * Get the token from shared pref and memory.
     * @return token
     */
    public Token getToken(){
        if(token==null) { token = mPrefs.getToken(); }
        return token;
    }

    public void getAuthToken(Playbasis playbasis, final OnResult<Token> listener){
        if(getToken()!=null){
            if(listener!=null)listener.onSuccess(token);
        }else{
            requestToken(playbasis, new OnResult<Token>() {
                @Override
                public void onSuccess(Token result) {
                    if(listener!=null)listener.onSuccess(token);
                }

                @Override
                public void onError(HttpError error) {
                    if(listener!=null) listener.onError(error);
                }
            });
        }
    }

    /**
     * Save the token
     * @param token
     */
    public void setAuthToken(Token token){
        this.token = token;
        mPrefs.saveToken(token);
    }

    /**
     * Clear the token. 
     */
    public void invalidateAuthToken() {
        token = null;
        mPrefs.clearToken();
    }

    /**
     * Static request token. 
     * @param playbasis
     * @param listener
     */
    public static void RequestToken(final Playbasis playbasis, final OnResult<Token> listener){
        AuthApi.auth(playbasis, listener);
    }

    /**
     * Static renew token. 
     * @param playbasis
     * @param listener
     */
    public static void RequestRenewToken(final Playbasis playbasis, final OnResult<Token> listener){
        AuthApi.authRenew(playbasis, listener);
    }

    /**
     * Get the token and save it. 
     * @param playbasis
     * @param listener
     */
    public void requestToken(final Playbasis playbasis, final OnResult<Token> listener){
        AuthApi.auth(playbasis, new OnResult<Token>() {
            @Override
            public void onSuccess(Token result) {
                setAuthToken(result);
                if(listener!=null) listener.onSuccess(result);
            }

            @Override
            public void onError(HttpError error) {
                if(listener!=null) listener.onError(error);
            }
        });
    }

    /**
     * Renew the token and save it. 
     * @param playbasis
     * @param listener
     */
    public void requestRenewToken(final Playbasis playbasis, final OnResult<Token> listener){
        AuthApi.authRenew(playbasis, new OnResult<Token>() {
            @Override
            public void onSuccess(Token result) {
                setAuthToken(result);
                if(listener!=null) listener.onSuccess(result);
            }

            @Override
            public void onError(HttpError error) {
                if(listener!=null) listener.onError(error);
            }
        });
    }

}
