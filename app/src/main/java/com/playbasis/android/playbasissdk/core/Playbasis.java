package com.playbasis.android.playbasissdk.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playbasis.android.playbasissdk.api.Api;
import com.playbasis.android.playbasissdk.api.AuthAuthenticator;
import com.playbasis.android.playbasissdk.api.EngineApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.http.HttpManager;
import com.playbasis.android.playbasissdk.http.toolbox.KeyStore;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.RuleAction;

/**
 * Created by gregoire barret on 2/13/15.
 * For PlayBasisSdk project.
 */
public class Playbasis {
    public static final String TAG = "Playbasis";

    private static Playbasis instance;

    private Context mContext;

    //TODO: merge KeyStore and Authenticator?
    private KeyStore mKeyStore;

    private HttpManager mHttpManager;

    private AuthAuthenticator authenticator;
    
    private String mChannel;

    /**
     * This method return the Playbasis singleton. If call before the builder return null.
     * @return Playbasis singleton.
     */
    @Nullable
    public static Playbasis getInstance() {
        return instance;
    }

    private Playbasis(PlayBasisContent playBasisContent) {
        this.mContext = playBasisContent.mContext;
        this.mKeyStore = playBasisContent.mKeyStore;
        this.mHttpManager = HttpManager.getInstance(mContext);
        this.authenticator = new AuthAuthenticator(mContext, this);
    }

    



    public Context getContext() {
        return mContext;
    }

    public KeyStore getKeyStore() {
        return mKeyStore;
    }

    public HttpManager getHttpManager() {
        return mHttpManager;
    }

    public AuthAuthenticator getAuthenticator(){
        return authenticator;
    }

    public String getChannel() {
        return mChannel;
    }

    /**
     * Builder for Playbasis singleton
     */
    public static class Builder{
        private PlayBasisContent mPlayBasisContent;

        /**
         * Constructor using a context
         * @param context
         */
        public Builder(@NonNull Context context) {
            mPlayBasisContent = new PlayBasisContent();
            mPlayBasisContent.mContext = context;
        }

        /**
         * This method is mandatory. <p>
         * Set the api key, should be save securely on the app.
         * @param apiKey Secret key given by PlayBasis.
         * @return
         */
        public Builder setApiKey(@NonNull String apiKey){
            this.mPlayBasisContent.mKeyStore.setApiKey(apiKey);
            return this;
        }

        /**
         * This method is mandatory. <p>
         * Set the api secret, should be save securely on the app 
         * @param apiSecret Secret key given by PlayBasis.
         * @return
         */
        public Builder setApiSecret(@NonNull String apiSecret){
            this.mPlayBasisContent.mKeyStore.setApiSecret(apiSecret);
            return this;
        }
        
        public Builder setChannel(String channel){
            this.mPlayBasisContent.mChannel = channel;
            return this;
        }

        /**
         * Create the Playbasis singleton
         * @return Playbasis singleton (can be access by Playbasis.getInstance())
         */
        public Playbasis build(){
            if(instance==null){
                instance = new Playbasis(mPlayBasisContent);
            }
            else {
                instance.mContext = mPlayBasisContent.mContext;
                instance.mKeyStore = mPlayBasisContent.mKeyStore;
            }
            //Send stored requests
            Api.resendRequests(instance);
            return instance;
        }
    }
    private static class PlayBasisContent{
        private Context mContext;
        private KeyStore mKeyStore = new KeyStore();
        private String mChannel;
    }


    /**
     * Return true if the network is available
     * @return network is available.
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    
    
    //Engine rules

    /**
     * High level function who make a sync engine rule request to the backend.
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from ActionRule Enum.
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, RuleAction action, OnResult<Rule> listener){
        EngineApi.rule(this, false, action.toString(), playerId, null, null,null, listener );
    }

    /**
     *  {@link #Do(String, com.playbasis.android.playbasissdk.model.RuleAction,
     *     com.playbasis.android.playbasissdk.api.OnResult)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed.
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, String action, OnResult<Rule> listener){
        EngineApi.rule(this, false, action, playerId, null, null,null, listener );
    }

    /**
     *  {@link #Do(String, com.playbasis.android.playbasissdk.model.RuleAction,
     *     com.playbasis.android.playbasissdk.api.OnResult)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from ActionRule Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, RuleAction action, String url, OnResult<Rule> listener){
        EngineApi.rule(this, false, action.toString(), playerId, url, null,null, listener );
    }

    /**
     *  {@link #Do(String, com.playbasis.android.playbasissdk.model.RuleAction,
     *     com.playbasis.android.playbasissdk.api.OnResult)}
     * @param playerId Player id as used in client's website.
     * @param action AName of the action performed.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, String action, String url, OnResult<Rule> listener){
        EngineApi.rule(this, false, action, playerId, url, null,null, listener );
    }


    /**
     *  {@link #Do(String, com.playbasis.android.playbasissdk.model.RuleAction,
     *     com.playbasis.android.playbasissdk.api.OnResult)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from ActionRule Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, RuleAction action, String url, String reward, OnResult<Rule> listener){
        EngineApi.rule(this, false, action.toString(), playerId, url, reward, null, listener );
    }

    /**
     *  {@link #Do(String, com.playbasis.android.playbasissdk.model.RuleAction,
     *     com.playbasis.android.playbasissdk.api.OnResult)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, String action, String url, String reward, OnResult<Rule> listener){
        EngineApi.rule(this, false, action, playerId, url, reward, null, listener );
    }

    /**
     *  {@link #Do(String, com.playbasis.android.playbasissdk.model.RuleAction,
     *     com.playbasis.android.playbasissdk.api.OnResult)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from ActionRule Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param quantity amount of the point-based reward to give to player, if the action trigger custom-point reward 
     *                  that doesn't specify reward quantity
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, RuleAction action, String url, String reward, String quantity, 
                   OnResult<Rule> listener){
        EngineApi.rule(this, false, action.toString(), playerId, url, reward, quantity, listener );
    }


    /**
     *  {@link #Do(String, com.playbasis.android.playbasissdk.model.RuleAction,
     *     com.playbasis.android.playbasissdk.api.OnResult)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param quantity amount of the point-based reward to give to player, if the action trigger custom-point reward 
     *                  that doesn't specify reward quantity
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, String action, String url, String reward, String quantity,
                   OnResult<Rule> listener){
        EngineApi.rule(this, false, action, playerId, url, reward, quantity, listener );
    }
    
    /**
     * High level function who make an async engine rule request to the backend.
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from ActionRule Enum.
     */
    public void Track(@NonNull String playerId, RuleAction action){
        EngineApi.rule(this, true, action.toString(), playerId, null, null,null, null );
    }

    /**
     * High level function who make an async engine rule request to the backend.
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed.
     */
    public void Track(@NonNull String playerId, String action){
        EngineApi.rule(this, true, action, playerId, null, null,null, null );
    }

    /**
     * {@link #Track(String, com.playbasis.android.playbasissdk.model.RuleAction)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from ActionRule Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     */
    public void Track(@NonNull String playerId, RuleAction action, String url){
        EngineApi.rule(this, true, action.toString(), playerId, url, null,null, null );
    }

    /**
     * {@link #Track(String, com.playbasis.android.playbasissdk.model.RuleAction)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     */
    public void Track(@NonNull String playerId, String action, String url){
        EngineApi.rule(this, true, action, playerId, url, null,null, null );
    }

    /** 
     * {@link #Track(String, com.playbasis.android.playbasissdk.model.RuleAction)}
     * @param playerId Player id as used in client's website.
     * @param actionName of the action performed from ActionRule Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     */
    public void Track(@NonNull String playerId, RuleAction action, String url, String reward){
        EngineApi.rule(this, true, action.toString(), playerId, url, reward,null, null );
    }

    /**
     * {@link #Track(String, com.playbasis.android.playbasissdk.model.RuleAction)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     */
    public void Track(@NonNull String playerId, String action, String url, String reward){
        EngineApi.rule(this, true, action, playerId, url, reward,null, null );
    }

    /**
     * {@link #Track(String, com.playbasis.android.playbasissdk.model.RuleAction)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from ActionRule Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging, 
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param quantity amount of the point-based reward to give to player, if the action trigger custom-point reward 
     *                  that doesn't specify reward quantity
     */
    public void Track(@NonNull String playerId, RuleAction action, String url, String reward, String quantity) {
        EngineApi.rule(this, true, action.toString(), playerId, url, reward, reward, null );
    }

    /**
     * {@link #Track(String, com.playbasis.android.playbasissdk.model.RuleAction)}
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging,
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param quantity amount of the point-based reward to give to player, if the action trigger custom-point reward
     *                  that doesn't specify reward quantity
     */
    public void Track(@NonNull String playerId, String action, String url, String reward, String quantity) {
        EngineApi.rule(this, true, action, playerId, url, reward, reward, null );
    }
    
}
