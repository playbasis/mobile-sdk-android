package com.playbasis.android.playbasissdk.http.toolbox;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by gregoire barret on 2/17/15.
 * For PlayBasisSdk project.
 */
public interface HttpModel {
    public Boolean isValid();
    public List<NameValuePair> toParams();
    
}
