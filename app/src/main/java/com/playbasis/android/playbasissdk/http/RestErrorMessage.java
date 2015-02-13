package com.playbasis.android.playbasissdk.http;

import java.util.List;

/**
 * Created by gregoire on 1/19/15.
 */
public class RestErrorMessage {

    public List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String toString(){
        if (errors!=null){
            String message = "";
            for (String error : errors){
                message += error;
                message += ", ";
            }
            message = message.substring(0, message.length()-2);
            return message;
        }
        return null;
    }

}
