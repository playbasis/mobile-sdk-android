/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.playbasis.android.playbasissdk.http;

/**
 * Exception style class encapsulating Http errors
 */
@SuppressWarnings("serial")
public class HttpError extends Exception {
    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    public HttpError() {
        networkResponse = null;
    }

    public HttpError(NetworkResponse response) {
        networkResponse = response;
    }

    public HttpError(String exceptionMessage) {
       super(exceptionMessage);
       networkResponse = null;
    }

    public HttpError(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        networkResponse = null;
    }

    public HttpError(Throwable cause) {
        super(cause);
        networkResponse = null;
    }

    /* package */ void setNetworkTimeMs(long networkTimeMs) {
       this.networkTimeMs = networkTimeMs;
    }

    public long getNetworkTimeMs() {
       return networkTimeMs;
    }
    

}
