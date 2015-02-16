package com.playbasis.android.playbasissdk.api;

import com.playbasis.android.playbasissdk.http.AuthFailureError;
import com.playbasis.android.playbasissdk.http.toolbox.Authenticator;

import java.util.Date;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class AuthAuntenticator implements Authenticator {
    public static final String TAG = "AuthAuntenticator";
    
    public String token;
    
    public Date expirationDate;

    @Override
    public String getAuthToken() throws AuthFailureError {
        return null;
    }

    @Override
    public void invalidateAuthToken(String authToken) {

    }
}
