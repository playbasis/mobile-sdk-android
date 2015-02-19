package com.playbasis.android.playbasissdk.core;

/**
 * Created by gregoire barret on 2/17/15.
 * For PlayBasisSdk project.
 */
public class SDKUtil {
    public static final String TAG = "Utils";
    
    public static final String SERVER_URL = "https://api.pbapp.net";
    public static final String SERVER_URL_ASYNC = SERVER_URL + "/Async";

    public static final String PLAYER_URL = "/Player";
    public static final String _PLAYER_URL = PLAYER_URL + "/";
    
    public static final String BADGES_URL = "/Badges";
    public static final String _BADGES_URL = BADGES_URL + "/";

    public static final String BADGE_URL = "/Badge";
    public static final String _BADGE_URL = BADGE_URL + "/";
    
    public static final String GOODS_URL = "/Goods";
    public static final String _GOODS_URL = GOODS_URL + "/";
    
    public static final String QUEST_URL = "/Quest";
    public static final String _QUEST_URL = QUEST_URL + "/";
    
    public static final String QUIZ_URL = "/Quiz";
    public static final String _QUIZ_URL = QUIZ_URL + "/";
    
    public static final String REDEEM_API = "/Redeem";
    public static final String _REDEEM_API = REDEEM_API + "/";
    
    public static final String _EMAIL_API = "/Email/";
    public static final String _SMS_API = "/Sms/";
    public static final String _PUSH_API = "/Push/";


    public static final String GOOD_GROUP_URL = "/Redeem/goodsGroup";

    public static final String DEFAULT_IMAGE_URL = "https://www.pbapp.net/images/default_profile.jpg";
    
    public static String getServerUrl(Boolean async){
        return async ? SERVER_URL_ASYNC : SERVER_URL;
    }
}
