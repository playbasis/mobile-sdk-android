package com.playbasis.android.playbasissdk.helper;

/**
 * Created by gregoire barret on 2/17/15.
 * For PlayBasisSdk project.
 */
public class StringHelper {
    public static final String TAG = "StringHelper";

    /**
     * Remove the last caratere of a string. 
     * @param str String to remove the last character
     * @return str without the last character
     */
    public static String removeLastChar(String str) {
        if (str!=null && str.length() > 0) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    /**
     * Remove the first occurrence found on a string
     * @param occurrence occurrence to remove
     * @param s string to edit
     * @return String without the first occurrence of "occurrence" params
     */
    public static String removeFirstOccurrence(String occurrence, String s){
        int index = s.indexOf(occurrence);
        if (index == -1)
        {
            return s;
        }
        else
        {
            return  s.substring(0, index) +
                    s.substring(index + occurrence.length());
        }
    }
}
