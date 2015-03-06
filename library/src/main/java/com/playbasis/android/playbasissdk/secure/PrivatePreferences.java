package com.playbasis.android.playbasissdk.secure;

import android.content.Context;

import com.playbasis.android.playbasissdk.api.AuthToken;

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
    
    
    private static final String ELAPSED = "elapsed";
    
    private static final String LASTED_DATE = "lasted_date";

    /**
     * Secure shared preference 
     */
    private final SecurePreferences mSecurePrefs;
    
    public PrivatePreferences(Context context){
        mSecurePrefs = new SecurePreferences(context);
    }

    /**
     * Get the Auth token object saved on the secure shared preferences.
     * @return Authentication token object.
     */
    public AuthToken getToken(){
        String sharedToken = mSecurePrefs.getString(PREF_TOKEN, null);
        Date sharedDate = new Date(mSecurePrefs.getLong(PREF_EXP_DATE, 0l));
        // if no date saved before
        if(sharedToken==null || sharedDate.equals(new Date(0l))){
            return null;
        }else{
            return new AuthToken(sharedToken, sharedDate);
        }
    }

    /**
     *  Save the AuthToken object on the secure shared preferences.
     * @param authToken AuthToken object
     */
    public void saveToken(AuthToken authToken){
        if(authToken ==null)return;
        
        mSecurePrefs.edit()
                .putString(PREF_TOKEN, authToken.getToken())
                .commit();
        
        if(authToken.getDateExpire()!=null)
            mSecurePrefs.edit()
                    .putLong(PREF_EXP_DATE, authToken.getDateExpire().getTime())
                    .commit();
    }

    /**
     * Clean the secure shared preferences
     */
    public void clearToken(){
        mSecurePrefs.edit().remove(PREF_TOKEN).commit();
        mSecurePrefs.edit().remove(PREF_EXP_DATE).commit();
        
    }

    public void clearDate(){
        mSecurePrefs.edit().remove(LASTED_DATE).commit();
        mSecurePrefs.edit().remove(ELAPSED).commit();

    }
    
    
    public long getLastedDate(){
        return mSecurePrefs.getLong(LASTED_DATE, 0l);
    }
    
    public void saveLastedDate(long date){
        mSecurePrefs.edit()
                .putLong(LASTED_DATE, date)
                .commit();
        
    }
    
    public Long getElapse(){
        return mSecurePrefs.getLong(ELAPSED, 0l);
        
    }
    
    public void saveElapse(long elapse){
        mSecurePrefs.edit()
                .putLong(ELAPSED, elapse)
                .commit();

    }
    
}
