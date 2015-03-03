package com.playbasis.android.sample;

import android.app.Application;

import com.playbasis.android.playbasissdk.core.Playbasis;

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
/*                .setApiKey("3416989394")
                .setApiSecret("b1fa1529410702557a6fe2f3913768a0")*/
                .build();
        playbasis.getAuthenticator().getAuthToken(null);
        
    }
}
