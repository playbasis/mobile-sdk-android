package com.playbasis.android.playbasissdk.helper;

/**
 * Created by gregoire barret on 2/13/15.
 * For PlayBasisSdk project.
 */
public class Validator {
    public static final String TAG = "Validator";

    /**
     * Check if it's a non null string i.e. a not null, not empty and not "null" string.
     * @param s String to test.
     * @return true if valid, false id not.
     */
    public static Boolean isValid(String s){
        return s != null && !s.trim().equals("") && !s.trim().equals("null");
        
    }
}
