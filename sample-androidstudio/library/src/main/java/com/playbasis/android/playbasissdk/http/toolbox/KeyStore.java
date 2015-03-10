package com.playbasis.android.playbasissdk.http.toolbox;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.playbasis.android.playbasissdk.helper.Validator;

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
    
    //Get the apiKey and apiSecret from manifest if not valid.
    public void generateKeyStore(Context context){
        if(context==null)return;


        if(!Validator.isValid(apiSecret)){
            try {
                ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
                Bundle bundle = ai.metaData;
                apiSecret = (String) bundle.get("com.playbasis.android.playbasissdk.secret");
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
            } catch (NullPointerException e) {
                Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
            }
        }

        if(!Validator.isValid(apiKey)){
            try {
                ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
                Bundle bundle = ai.metaData;
                apiKey = (String) bundle.get("com.playbasis.android.playbasissdk.apikey");
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
            } catch (NullPointerException e) {
                Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
            }
        }
    }
    
    

}
