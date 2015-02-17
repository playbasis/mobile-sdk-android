package com.playbasis.android.playbasissdk.helper;

/**
 * Created by gregoire barret on 2/17/15.
 * For PlayBasisSdk project.
 */
public class StringHelper {
    public static final String TAG = "StringHelper";

    public static String removeLastChar(String str) {
        if (str!=null && str.length() > 0) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }
}
