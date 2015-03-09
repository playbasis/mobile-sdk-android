package com.playbasis.android.playbasissdk.api;

import android.content.Context;
import android.support.annotation.Nullable;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.secure.PrivatePreferences;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class AuthAuthenticator {
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
    public AuthToken authToken;

    /**
     * Playbasis reference. 
     */
    private Playbasis playbasis;

    /**
     * init context and secure shared pref. 
     * @param context
     */
    private AuthAuthenticator(Context context) {
        this.mContext = context;
        this.mPrefs = new PrivatePreferences(mContext);
    }
    
    public AuthAuthenticator(Context context, Playbasis playbasis){
        this(context);
        this.playbasis = playbasis;
        
    }

    public AuthAuthenticator(Playbasis playbasis){
        this(playbasis.getContext());
        this.playbasis = playbasis;

    }

    /**
     *  Synchronously get the saved key token. If no token already init, return null.
     * @return AuthToken
     */
    @Nullable public String getToken() {
        //Try get token saved on shared preferences.
        if(authToken ==null) { authToken = mPrefs.getToken(); }
        return authToken == null ? "" : authToken.getToken();
    }

    /**
     * Get the token from shared pref and memory.
     * @return token
     */
    private AuthToken getSavedAuthToken(){
        if(authToken ==null) { authToken = mPrefs.getToken(); }
        return authToken;
    }

    /**
     * Get auth token form local, and make the request if not available.
     * @param listener
     */
    public void getAuthToken(final OnResult<AuthToken> listener){
        if(getSavedAuthToken()!=null){
            if(listener!=null)listener.onSuccess(authToken);
        }else{
            requestAuthToken(new OnResult<AuthToken>() {
                @Override
                public void onSuccess(AuthToken result) {
                    if(listener!=null)listener.onSuccess(authToken);
                }

                @Override
                public void onError(HttpError error) {
                    if(listener!=null) listener.onError(error);
                }
            });
        }
    }

    /**
     * Get auth token form local, and make the request if not available.
     */
    public void getAuthToken(){
        if(getSavedAuthToken()==null){
            requestAuthToken(null);
        }
    }

    /**
     * Save the token
     * @param authToken
     */
    public void setAuthToken(AuthToken authToken){
        this.authToken = authToken;
        mPrefs.saveToken(authToken);
    }

    /**
     * Clear the token. 
     */
    public void invalidateAuthToken() {
        authToken = null;
        mPrefs.clearToken();
    }

    /**
     * Static request token. 
     * @param playbasis
     * @param listener
     */
    public static void RequestAuthToken(final Playbasis playbasis, final OnResult<AuthToken> listener){
        AuthApi.auth(playbasis, listener);
    }

    /**
     * Static renew token. 
     * @param playbasis
     * @param listener
     */
    public static void RequestRenewAuthToken(final Playbasis playbasis, final OnResult<AuthToken> listener){
        AuthApi.authRenew(playbasis, listener);
    }

    /**
     * Get the token and save it. 
     * @param listener
     */
    public void requestAuthToken(final OnResult<AuthToken> listener){
        AuthApi.auth(playbasis, new OnResult<AuthToken>() {
            @Override
            public void onSuccess(AuthToken result) {
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
     * @param listener
     */
    public void requestRenewAuthToken(final OnResult<AuthToken> listener){
        AuthApi.authRenew(playbasis, new OnResult<AuthToken>() {
            @Override
            public void onSuccess(AuthToken result) {
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
