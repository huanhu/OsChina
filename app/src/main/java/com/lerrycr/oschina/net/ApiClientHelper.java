package com.lerrycr.oschina.net;

import android.app.Activity;

import com.lerrycr.oschina.listener.OnResponseListener;

/**
 * Created by Lerry on 2016/10/30.
 */

public class ApiClientHelper {
    private static final String HOST_URL = "http://192.168.96.25:8080/";
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

    /**
     * 获取咨询和热点的详情页
     */
    public static String NEWS_DETAIL_URL = HOST_URL + "oschina/detail/news_detail/";

    /**
     * 获取博客和推荐页的详情
     */
    public static String BLOG_DETAIL_URL = HOST_URL + "oschina/detail/blog_detail/";

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

    /**
     * 获取新闻详情页面的数据
     *
     * @param activity
     * @param index
     * @param listener
     */
    public static void getNewsAndHotsDetial(Activity activity, int index, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, NEWS_DETAIL_URL + index + ".xml", listener);
    }

    /**
     * 获取博客和推荐详情页的数据
     *
     * @param activity
     * @param index
     * @param listener
     */
    public static void getBlogAndRecommendDetial(Activity activity, int index, OnResponseListener listener) {
        ApiHttpClient.enqueue(activity, BLOG_DETAIL_URL + index + ".xml", listener);
    }

}
