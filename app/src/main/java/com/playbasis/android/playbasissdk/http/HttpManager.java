package com.playbasis.android.playbasissdk.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.playbasis.android.playbasissdk.http.toolbox.Http;
import com.playbasis.android.playbasissdk.http.toolbox.ImageLoader;
import com.playbasis.android.playbasissdk.http.toolbox.KeyStore;

/**
 * Created by gregoire on 1/8/15.
 */
public class HttpManager {
    private static HttpManager instance;
    private Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private HttpManager(Context context) {
        this.mContext = context;
        mRequestQueue = Http.newRequestQueue(context);
    }


    public static synchronized HttpManager getInstance(Context context) {
        if (instance == null) {
            instance = new HttpManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Http.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    /**
     * Add a new request to the requestQueue 
     * @param req Request
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag("App");
        getRequestQueue().add(req);
    }

    /**
     * Add a new request to the requestQueue with a tag.
     * @param req Request.
     * @param tag Request tag.
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    /**
     * Cancel all request with the tag.
     * @param tag Request tag.
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    

}
