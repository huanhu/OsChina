package com.lerrycr.oschina.net;

import android.app.Activity;

import com.lerrycr.oschina.listener.OnResponseListener;

/**
 * Created by Lerry on 2016/10/30.
 */

public class ApiClientHelper {
    private static final String HOST_URL = "http://192.168.96.24:8080/";
    /**
     * 综合内的资讯url
     */
    public static String MESSAGE_URL = HOST_URL + "oschina/list/news/page0.xml";

    /**
     * 综合内的热点url
     */
    public static String HOTS_URL = HOST_URL + "oschina/list/hots/page0.xml";
    /**
     * 综合内的博客url
     */
    public static String BLOG_URL = HOST_URL + "oschina/list/blog/page0.xml";
    /**
     * 综合内的推荐url
     */
    public static String RECOMMAND_URL = HOST_URL + "oschina/list/blog/page0.xml";


    public static void getMessages(Activity activity, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, MESSAGE_URL, listener);
    }

    public static void getHots(Activity activity, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, HOTS_URL, listener);
    }

    public static void getBlog(Activity activity, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, BLOG_URL, listener);
    }

    public static void getRecommand(Activity activity, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, RECOMMAND_URL, listener);
    }
}
