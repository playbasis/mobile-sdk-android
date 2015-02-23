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
    
    public static String removeFirstOccurence(String occurence, String s){
        int index = s.indexOf(occurence);
        if (index == -1)
        {
            return s;
        }
        else
        {
            return  s.substring(0, index) +
                    s.substring(index + occurence.length());
        }
    }
}
