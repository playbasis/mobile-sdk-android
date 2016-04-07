package com.playbasis.android.sample;

import android.app.Application;

import com.playbasis.android.playbasissdk.api.AuthToken;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.HttpError;

/**
 * Created by gregoire barret on 3/2/15.
 * For PlayBasisSdk project.
 */
public class SampleApplication extends Application {
    public static final String TAG = "Application";

    public static Playbasis playbasis;
    
    @Override
    public void onCreate() {
        super.onCreate();

        playbasis = new Playbasis.Builder(this)
                .setBackendUrl("https://starhub-api.playbasis.com/")
                .build();
        
        playbasis.getAuthenticator().getAuthToken();
        
    }
}
