package com.playbasis.android.playbasissdk.http.toolbox;

/**
 * Created by gregoire barret on 2/13/15.
 * For PlayBasisSdk project.
 */
//TODO: Add encryption
public class KeyStore {
    public static final String TAG = "KeyStore";
    
    private String apiKey;
    private String apiSecret;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

}
