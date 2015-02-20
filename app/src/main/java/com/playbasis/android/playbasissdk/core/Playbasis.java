package com.playbasis.android.playbasissdk.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playbasis.android.playbasissdk.api.AuthAuthenticator;
import com.playbasis.android.playbasissdk.http.HttpManager;
import com.playbasis.android.playbasissdk.http.toolbox.KeyStore;

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
    
}
