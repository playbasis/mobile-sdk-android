package com.playbasis.android.playbasissdk.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

/**
 * Playbasis backend response error 
 */
@SuppressWarnings("serial")
public class RequestError {
    public static final String TAG = "RequestError";

    /**
     * Message of the error 
     */
    public final String message;

    /**
     * Code of the error {@link com.playbasis.android.playbasissdk.http.RequestError.ERROR_CODE}
     */
    public final int errorCode;

    public RequestError(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
    
    public RequestError(PlaybasisResponse response){
        this.message = response.message;
        this.errorCode = response.errorCode;
        
    }

    /**
     * Create an error when not network are found.
     * @return RequestError
     */
    public static RequestError NoNetwork(){
        return new RequestError("No network available", ERROR_CODE.NO_NETWORK_ERROR);
        
    }

    @Override
    public String toString() {
        return "RequestError{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }

    /**
     * Constants enumerating the server error status codes.
     */
    public interface ERROR_CODE {
        /**
         *  Invalid token Key
         */
        public int INVALID_TOKEN = 900;
        /**
         *  Request exceeded , Too much request
         */
        public int REQUEST_EXCEEDED = 901;
        /**
         *  Token key required
         */
        public int TOKEN_REQUIRED = 902;
        /**
         *  Invalid parameter , must not be blank and special character
         */
        public int PARAMETER_MISSING = 903;
        /**
         *  There is an internal server error
         */
        public int INTERNAL_ERROR = 800;
        /**
         *  Email error, cannot send email
         */
        public int CANNOT_SEND_EMAIL = 801;
        /**
         *  Email error, all designated recipients are in black list
         */
        public int ALL_EMAILS_IN_BLACKLIST = 802;
        /**
         *  Email is already in black list
         */
        public int EMAIL_ALREADY_IN_BLACKLIST = 803;
        /**
         *  Email is not in black list
         */
        public int EMAIL_NOT_IN_BLACKLIST = 804;
        /**
         *  This Amazon SNS message type is not supported
         */
        public int UNKNOWN_SNS_MESSAGE_TYPE = 805;
        /**
         *  Unknown notification message
         */
        public int UNKNOWN_NOTIFICATION_MESSAGE = 806;
        /**
         *  Cannot verify the authenticity of PayPal IPN message
         */
        public int CANNOT_VERIFY_PAYPAL_IPN = 807;
        /**
         *  Invalid PayPal IPN
         */
        public int INVALID_PAYPAL_IPN = 808;
        /**
         *  Invalid API-KEY OR API-SECRET
         */
        public int INVALID_API_KEY_OR_SECRET = 1;
        /**
         *  Can't Access ,Permission Denied
         */
        public int ACCESS_DENIED = 2;
        /**
         *  Limit Exceed, Contact Admin
         */
        public int LIMIT_EXCEED = 3;
        /**
         *  User doesn't exist
         */
        public int USER_NOT_EXIST = 200;
        /**
         *  User alredy exist
         */
        public int USER_ALREADY_EXIST = 201;
        /**
         *  User registration limit exceed
         */
        public int TOO_MANY_USERS = 202;
        /**
         *  The user or reward type does not exist
         */
        public int USER_OR_REWARD_NOT_EXIST = 203;
        /**
         *  cl_player_id format should be 0-9a-zA-Z_-
         */
        public int USER_ID_INVALID = 204;
        /**
         *  phone number format should be +[countrycode][number] example. +66861234567
         */
        public int USER_PHONE_INVALID = 205;
        /**
         *  The user has no such reward
         */
        public int REWARD_FOR_USER_NOT_EXIST = 206;
        /**
         *  The user has not enough reward
         */
        public int REWARD_FOR_USER_NOT_ENOUGH = 207;
        /**
         *  Action not available
         */
        public int ACTION_NOT_FOUND = 301;
        /**
         *  Reward not available
         */
        public int REWARD_NOT_FOUND = 401;
        /**
         *  Goods not available
         */
        public int GOODS_NOT_FOUND = 501;
        /**
         *  User has exceeded redeem limit
         */
        public int OVER_LIMIT_REDEEM = 601;
        /**
         *  User has already join this quest
         */
        public int QUEST_JOINED = 701;
        /**
         *  User has finished this quest
         */
        public int QUEST_FINISHED = 702;
        /**
         *  User has no permission to join this quest
         */
        public int QUEST_CONDITION = 703;
        /**
         *  User has not yet join this quest
         */
        public int QUEST_CANCEL_FAILED = 704;
        /**
         *  Quest not found
         */
        public int QUEST_JOIN_OR_CANCEL_NOTFOUND = 705;
        /**
         *  Quiz not found
         */
        public int QUIZ_NOT_FOUND = 1001;
        /**
         *  Question not found
         */
        public int QUIZ_QUESTION_NOT_FOUND = 1002;
        /**
         *  Option not found
         */
        public int QUIZ_OPTION_NOT_FOUND = 1003;
        /**
         *  Question has already been completed by the player
         */
        public int QUIZ_QUESTION_ALREADY_COMPLETED = 1004;
        /**
         *  Question has already been completed by the player
         */
        public int NO_NETWORK_ERROR = 1007;
        /**
         *  Uknown
         */
        public int DEFAULT = 9999;

    }
}
