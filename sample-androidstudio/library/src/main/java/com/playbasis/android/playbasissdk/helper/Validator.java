package com.playbasis.android.playbasissdk.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gregoire barret on 2/13/15.
 * For PlayBasisSdk project.
 */
public class Validator {
    public static final String TAG = "Validator";

    public static String validate(String s){
        String validString = "";
        if(isValid(s)){
            validString = s;
        }
        return validString;
    }

    /**
     * Check if it's a non null string i.e. a not null, not empty and not "null" string.
     * @param s String to test.
     * @return true if valid, false id not.
     */
    public static Boolean isValid(String s){
        return s != null && !s.trim().equals("") && !s.trim().equals("null");
    }

    /**
     * Return true of the object is not null. 
     * @param o object to validate
     * @return true if object is not null
     */
    public static Boolean isValid(Object o){
        return o != null;
    }

    /**
     * Return true if the string contain only alpha numeric values. 
     * @param s string to test
     * @return true if string contain only [A-Za-z0-9_-]
     */
    public static Boolean isValidAlphaNum(String s){
       return isValid(s) && s.matches("[A-Za-z0-9_-]+");
    }

    /**
     * Return true if the String is a valid email 
     * @param email string to validate
     * @return true if the String is a valid email 
     */
    public static Boolean isValidEmail(String email){
        if(!isValid(email))return false;
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "[_A-Za-z0-9-.]+@[A-Za-z0-9_-]+[.][A-Za-z0-9.]+";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email.trim());
        return matcher.matches();
    }

    /**
     * Return true if the String is a valid playbasis email
     * @param email string to validate
     * @return true if the String is a valid playbasis email
     */
    public static Boolean isValidPlaybasisEmail(String email){
        if(!isValidEmail(email))return false;
        else if(email.toLowerCase().contains("noreply")) return false;
        else if(email.toLowerCase().equals("pbapp_auto_user@playbasis.com")) return false;
        else return true;
        
    }

    /**
     * Return true if the String is a valid phone
     * @param phone string to validate
     * @return true if the String is a valid phone
     */
    public static Boolean isValidPhone(String phone){
        if(!isValid(phone))return false;
        else if(phone.trim().equals("+66861234567"))return false;
        Pattern pattern;
        Matcher matcher;
        final String PHONE_PATTERN = "[+][0-9]{10,13}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone.trim());
        return matcher.matches();
    }
}
