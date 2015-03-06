package com.playbasis.android.playbasissdk.api;

import android.os.SystemClock;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.DateHelper;
import com.playbasis.android.playbasissdk.http.NTP_UTC_Time;
import com.playbasis.android.playbasissdk.http.RequestError;
import com.playbasis.android.playbasissdk.secure.PrivatePreferences;

/**
 * Created by gregoire barret on 3/6/15.
 * For PlayBasisSdk project.
 */
public class NTPdate {
    public static final String TAG = "NTPdate";
    
    public interface OnDate{
        public void onDate(Long date);
        public void onError(RequestError error);
        
    }
    
    
    public static void GetNTPDate(final Playbasis playbasis, final OnDate listener){
        final NTP_UTC_Time client = new NTP_UTC_Time();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (client.requestTime("pool.ntp.org", 2000)) {
                    long now = 0;
                    now = client.getNtpTime();
                    PrivatePreferences preferences =  new PrivatePreferences(playbasis.getContext());
                    preferences.saveElapse(SystemClock.elapsedRealtime());
                    preferences.saveLastedDate(now);
                    if(listener!=null) listener.onDate(now);
                }else{
                    if(listener!=null) listener.onError(new RequestError("dateErrors", RequestError.ERROR_CODE.DEFAULT));
                }

            }
        }).start();
    }
    public static long GetLocalDate(final Playbasis playbasis){
        PrivatePreferences preferences =  new PrivatePreferences(playbasis.getContext());
        long lastedDate = preferences.getLastedDate();
        long lastedElapse = preferences.getElapse();
        
        if(lastedDate > 0l){
            return (lastedDate + (SystemClock.elapsedRealtime() - lastedElapse));
        }else{
            return DateHelper.currentTimetamp();
        }
    }
    
    
}
