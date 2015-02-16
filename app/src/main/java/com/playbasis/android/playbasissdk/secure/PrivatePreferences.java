package com.playbasis.android.playbasissdk.secure;

import android.content.Context;

import com.playbasis.android.playbasissdk.api.Token;

import java.util.Date;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class PrivatePreferences {
    public static final String TAG = "PrivatePreferences";

    /**
     * Key of the token value on the secure shared preference. 
     */
    private static final String PREF_TOKEN = "pref_token";
    /**
     * Key of the expiration date on the secure shared preference. 
     */
    private static final String PREF_EXP_DATE = "pref_exp_date";

    /**
     * Secure shared preference 
     */
    private final SecurePreferences mSecurePrefs;
    
    public PrivatePreferences(Context context){
        mSecurePrefs = new SecurePreferences(context);
    }
    
    public Token getToken(){
        String sharedToken = mSecurePrefs.getString(PREF_TOKEN, null);
        Date sharedDate = new Date(mSecurePrefs.getLong(PREF_EXP_DATE, 0l));
        // if no date saved before
        if(sharedToken==null || sharedDate.equals(new Date(0l))){
            return null;
        }else{
            return new Token(sharedToken, sharedDate);
        }
    }
    
    public void saveToken(Token token){
        if(token==null)return;
        
        mSecurePrefs.edit()
                .putString(PREF_TOKEN, token.getToken())
                .commit();
        
        if(token.getDateExpire()!=null)
            mSecurePrefs.edit()
                    .putLong(PREF_EXP_DATE, token.getDateExpire().getTime())
                    .commit();
    }
    
    public void clearToken(){
        mSecurePrefs.edit().remove(PREF_TOKEN).commit();
        mSecurePrefs.edit().remove(PREF_EXP_DATE).commit();
        
    }
}
