package com.playbasis.android.playbasissdk.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.playbasis.android.playbasissdk.api.Api;
import com.playbasis.android.playbasissdk.api.AuthAuthenticator;
import com.playbasis.android.playbasissdk.api.EngineApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.helper.DateHelper;
import com.playbasis.android.playbasissdk.http.HttpManager;
import com.playbasis.android.playbasissdk.http.toolbox.KeyStore;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.RuleAction;
import com.playbasis.android.playbasissdk.model.UIEvent;
import com.playbasis.android.playbasissdk.secure.PrivatePreferences;
import com.playbasis.android.playbasissdk.widget.AbstractPlayerView;

/**
 * Created by gregoire barret on 2/13/15.
 * For PlayBasisSdk project.
 */
public class Playbasis {
    public static final String TAG = "Playbasis";

    private static Playbasis instance;

    private Context mContext;
    
    private FragmentActivity mActivity;

    private KeyStore mKeyStore;

    private HttpManager mHttpManager;

    private AuthAuthenticator authenticator;
    
    private String mChannel;
    
    private AbstractPlayerView mPlayerView;
    
    private AbstractPlayerView mPlayerEmailView;
    
    private AbstractPlayerView mPlayerSmsView;

    /**
     * This method return the Playbasis singleton. If call before the builder return null.
     * @return Playbasis singleton.
     */
    @Nullable
    public static Playbasis getInstance() {
        return instance;
    }

    /**
     * Constructor used by the builder. 
     * @param playBasisContent builder handler.
     */
    private Playbasis(PlayBasisContent playBasisContent) {
        this.mContext = playBasisContent.context;
        this.mKeyStore = playBasisContent.keyStore;
        this.mChannel = playBasisContent.channel;
        this.mHttpManager = HttpManager.getInstance(mContext);
        this.authenticator = new AuthAuthenticator(mContext, this);
        this.mPlayerView = playBasisContent.playerView;
        this.mPlayerEmailView = playBasisContent.playerEmailView;
        this.mPlayerSmsView = playBasisContent.playerSmsView;


    }


    /**
     * Get Playbasis context.
     * @return context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Get Playbasis  key store.
     * @return key store
     */
    public KeyStore getKeyStore() {
        return mKeyStore;
    }

    /**
     * Get Playbasis httpManager.
     * @return httpManager
     */
    public HttpManager getHttpManager() {
        return mHttpManager;
    }

    /**
     * Get Playbasis authenticator.
     * @return authenticator
     */
    public AuthAuthenticator getAuthenticator(){
        return authenticator;
    }

    /**
     * Get Playbasis channel.
     * @return channel
     */
    public String getChannel() {
        return mChannel;
    }

    /**
     * Get Player dialog fragment. 
     * @return player view
     */
    public AbstractPlayerView getPlayerView(){
        return mPlayerView;
    }

    /**
     * Get Player Email view.
     * @return player email dialog fragment 
     */
    public AbstractPlayerView getPlayerEmailView(){return mPlayerEmailView;}

    /**
     * Get player Sms view.
     * @return player sms dialog fragment
     */
    public AbstractPlayerView getPlayerSmsView(){return mPlayerSmsView;}
    
    public void setActivity(FragmentActivity activity){
        this.mActivity = activity;
    }
    
    public FragmentActivity getActivity(){
        return mActivity;
    }
    
    public void removeActivity(){
        mActivity = null;
        
    }
    
    
    /**
     * Builder for Playbasis singleton
     */
    public static class Builder{
        private PlayBasisContent mPlayBasisContent;

        /**
         * Constructor using a context
         * @param context context
         */
        public Builder(@NonNull Context context) {
            mPlayBasisContent = new PlayBasisContent();
            mPlayBasisContent.context = context;
        }

        /**
         * This method is mandatory. <p>
         * Set the api key, should be save securely on the app.
         * @param apiKey Secret key given by PlayBasis.
         * @return builder
         */
        public Builder setApiKey(@NonNull String apiKey){
            this.mPlayBasisContent.keyStore.setApiKey(apiKey);
            return this;
        }

        /**
         * This method is mandatory. <p>
         * Set the api secret, should be save securely on the app 
         * @param apiSecret Secret key given by PlayBasis.
         * @return builder
         */
        public Builder setApiSecret(@NonNull String apiSecret){
            this.mPlayBasisContent.keyStore.setApiSecret(apiSecret);
            return this;
        }

        /**
         * Set channel params of async methods.
         * @param channel Optional. If you wish to see the response from this request, 
         *                 set this to the domain name  of your site.
         * See section "Receiving Responses from Asynchronous Calls" for more details.
         * @return builder
         */
        public Builder setChannel(String channel){
            this.mPlayBasisContent.channel = channel;
            return this;
        }

        /**
         * Set dialog fragment display when missing data player. 
         * @param playerView player dialog fragment
         * @return builder
         */
        public Builder setPlayerView(AbstractPlayerView playerView){
            this.mPlayBasisContent.playerView = playerView;
            return this;
        }

        /**
         * Set dialog fragment display when the player sms is missing on send sms message.
         * @param playerView player sms dialog fragment
         * @return builder
         */
        public Builder setPlayerSmsView(AbstractPlayerView playerView){
            this.mPlayBasisContent.playerSmsView = playerView;
            return this;
        }

        /**
         * Set dialog fragment display when the player email is missing on send email message.
         * @param playerView player email dialog fragment
         * @return builder
         */
        public Builder setPlayerEmailView(AbstractPlayerView playerView){
            this.mPlayBasisContent.playerEmailView= playerView;
            return this;
        }
        
        

        /**
         * Create the Playbasis singleton
         * @return Playbasis singleton (can be access by Playbasis.getInstance())
         */
        public Playbasis build(){
            mPlayBasisContent.keyStore.generateKeyStore(mPlayBasisContent.context);
            if(instance==null){
                instance = new Playbasis(mPlayBasisContent);
            }
            else {
                instance.mContext = mPlayBasisContent.context;
                instance.mKeyStore = mPlayBasisContent.keyStore;
                instance.mChannel = mPlayBasisContent.channel;
            }
            PrivatePreferences preferences = new PrivatePreferences(mPlayBasisContent.context);
            preferences.clearDate();
            //Send stored requests
            Api.resendRequests(instance);
            return instance;
        }
    }
    // handler for the playbasis constructor.
    private static class PlayBasisContent{
        public Context context;
        public KeyStore keyStore = new KeyStore();
        public String channel;
        public AbstractPlayerView playerView;

        public AbstractPlayerView playerEmailView;

        public AbstractPlayerView playerSmsView;
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
     * High level function who make a sync engine rule request to the backend.
     * @param playerId Player id as used in client's website.
     * @param action Name of the action performed from UIEvent Enum.
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, UIEvent action, OnResult<Rule> listener){
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
     * @param action Name of the action performed from UIEvent Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging,
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, UIEvent action, String url, OnResult<Rule> listener){
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
     * @param action Name of the action performed from UIEvent Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging,
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, UIEvent action, String url, String reward, OnResult<Rule> listener){
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
     * @param action Name of the action performed from UIEvent Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging,
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param quantity amount of the point-based reward to give to player, if the action trigger custom-point reward
     *                  that doesn't specify reward quantity
     * @param listener Callback interface.
     */
    public void Do(@NonNull String playerId, UIEvent action, String url, String reward, String quantity,
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
     * @param action Name of the action performed from UIEvent Enum.
     */
    public void Track(@NonNull String playerId, UIEvent action){
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
     * @param action Name of the action performed from UIEvent Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging,
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     */
    public void Track(@NonNull String playerId, UIEvent action, String url){
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
     * @param action Name of the action performed from ActionRule Enum.
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
     * @param action Name of the action performed from UIEvent Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging,
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     */
    public void Track(@NonNull String playerId, UIEvent action, String url, String reward){
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
     * @param action Name of the action performed from UIEvent Enum.
     * @param url URL of the page that trigger the action or any identifier string - Used for logging,
     *              URL specific  rules, and rules that trigger only when a specific identifier string is
     *              supplied
     * @param reward name of the point-based reward to give to player, if the action trigger custom-point reward that
     *                  doesn't specify reward name
     * @param quantity amount of the point-based reward to give to player, if the action trigger custom-point reward
     *                  that doesn't specify reward quantity
     */
    public void Track(@NonNull String playerId, UIEvent action, String url, String reward, String quantity) {
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
