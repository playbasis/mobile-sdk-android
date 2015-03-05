package com.playbasis.android.playbasissdk.helper;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;

import java.util.HashMap;

/**
 * Created by gregoire barret on 3/5/15.
 * For PlayBasisSdk project.
 */
public class ApiHelper {
    public static final String TAG = "ApiHelper";
    
    
    public static HashMap<String, String> getHeaderMap(Playbasis playbasis){
        HashMap<String, String> headers = new HashMap<String, String>();


        DisplayMetrics metrics = playbasis.getContext().getResources().getDisplayMetrics();
        String screenWidth = String.valueOf(metrics.widthPixels);
        String screenHeight = String.valueOf(metrics.heightPixels);

        String packageName = playbasis.getContext().getPackageName();
        String androidVersion = Build.VERSION.RELEASE;
        String deviceName = Build.DEVICE;
        String deviceBrand = Build.BRAND;
        String sdkVersion = SDKUtil.SDK_VERSION;
        
        
        headers.put("AppBundle", packageName);
        headers.put("screenWidth", screenWidth);
        headers.put("screenHeight", screenHeight);
        headers.put("osVersion", androidVersion);
        headers.put("deviceName", deviceName);
        headers.put("deviceBrand", deviceBrand);
        headers.put("sdkVersion", sdkVersion);
        headers.put("os", "android");

        return headers;
        
    }
}
