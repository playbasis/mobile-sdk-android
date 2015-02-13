package com.playbasis.android.playbasissdk.http;

import android.content.Context;

import com.playbasis.android.playbasissdk.http.toolbox.Http;
import com.playbasis.android.playbasissdk.http.toolbox.ImageLoader;

/**
 * Created by gregoire on 1/8/15.
 */
public class HttpController {
    private static HttpController instance;
    private Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private HttpController(Context context) {
        this.mContext = context;
        mRequestQueue = Http.newRequestQueue(context);
    }


    public static synchronized HttpController getInstance(Context context) {
        if (instance == null) {
            instance = new HttpController(context);
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

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag("App");
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
