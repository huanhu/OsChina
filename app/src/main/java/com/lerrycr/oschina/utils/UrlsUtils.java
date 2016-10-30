package com.lerrycr.oschina.utils;

import java.util.TreeMap;

/**
 * Created by Lerry on 2016/10/28.
 */

public class UrlsUtils {

    public static String createRequestUrl(String url, TreeMap<String, String> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        // 所参数拼接成：&index=0&age=48&page=5
        StringBuffer sb = new StringBuffer();
        for (String key : params.keySet()) {
            sb.append("&").append(key.trim()).append("=").append(params.get(key).trim());
        }

        sb.deleteCharAt(0); // 删除第1个字符“&"
        String requestUrl = url + "?" + sb.toString();

        Logger.i(UrlsUtils.class, requestUrl);
        return requestUrl;
    }
}
