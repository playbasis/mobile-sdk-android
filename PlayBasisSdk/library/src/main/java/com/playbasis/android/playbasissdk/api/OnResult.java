package com.playbasis.android.playbasissdk.api;

import com.playbasis.android.playbasissdk.http.HttpError;

/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public interface OnResult<T> {
    public void onSuccess(T result);
    public void onError(HttpError error);
}
