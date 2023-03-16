package com.util.androidcomponents;

import static com.android.volley.toolbox.Volley.newRequestQueue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;

public class NetworkControllerUtil {
    private static final int DEFAULT_TIME_OUT = 12 * 10000;

    private RequestQueue mRequestQueue;
    @SuppressLint("StaticFieldLeak")
    private static NetworkControllerUtil mNetworkController;
    private final Context mContext;

    private final DefaultRetryPolicy defaultRetryPolicy;

    private NetworkControllerUtil(Context context) {
        this.mContext = context;
        defaultRetryPolicy = new DefaultRetryPolicy(DEFAULT_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public static NetworkControllerUtil initialize(Context context) {
        if (mNetworkController == null) {
            mNetworkController = new NetworkControllerUtil(context);
        }
        return mNetworkController;
    }

    public static NetworkControllerUtil getInstance() {
        if (mNetworkController == null)
            throw new RuntimeException(NetworkControllerUtil.class + " not initialized");
        return mNetworkController;
    }

    public synchronized RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = newRequestQueue(mContext);
        }
        mRequestQueue.getCache().clear();
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(com.android.volley.Request<T> req, RetryPolicy retryPolicy) {
        req.setRetryPolicy(retryPolicy);
        req.setShouldCache(false);

        // set the default tag if tag is empty
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(com.android.volley.Request<T> req) {
        // set the default tag if tag is empty
        addToRequestQueue(req, defaultRetryPolicy);
    }

    // Check network connection
    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
