package com.playbasis.android.playbasissdk.http.toolbox;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.util.List;

/**
 * Created by gregoire on 1/12/15.
 */
public class ParamsHelper {
    public static String addParams(String url, List<NameValuePair> params){
        if(!url.endsWith("?"))
            url += "?";
        
        String paramString = URLEncodedUtils.format(params, "utf-8");
        url += paramString;
        return url;
    }
}
