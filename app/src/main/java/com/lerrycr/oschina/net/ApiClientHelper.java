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
    public static String MESSAGE_URL = HOST_URL + "oschina/list/news/page";

    /**
     * 综合内的热点url
     */
    public static String HOTS_URL = HOST_URL + "oschina/list/hotspot/page";
    /**
     * 综合内的博客url
     */
    public static String BLOG_URL = HOST_URL + "oschina/list/blog/page";
    /**
     * 综合内的推荐url
     */
    public static String RECOMMAND_URL = HOST_URL + "oschina/list/recommend/page";


    public static void getMessages(Activity activity, int index, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, MESSAGE_URL + index + ".xml", listener);
    }

    public static void getHots(Activity activity, int index, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, HOTS_URL + index + ".xml", listener);
    }

    public static void getBlog(Activity activity, int index, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, BLOG_URL + index + ".xml", listener);
    }

    public static void getRecommand(Activity activity, int index, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, RECOMMAND_URL + index + ".xml", listener);
    }


}
