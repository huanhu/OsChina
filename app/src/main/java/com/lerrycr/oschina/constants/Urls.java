package com.lerrycr.oschina.constants;

import com.lerrycr.oschina.MyApp;
import com.lerrycr.oschina.utils.PreferenceUtils;

/**
 * Created by Lerry on 2016/10/28.
 */

public abstract class Urls {
    public static String DEFAULTHOST = "http://192.168.96.24:8080/";
    private static String makeHost() {
        String host = DEFAULTHOST;
        String ipAndSocket = PreferenceUtils.getString(MyApp.getContext(), Constants.IP_ADDRESS, "");
        if (ipAndSocket.length() > 0) {
            String[] split = ipAndSocket.split("#");
            String ip = split[0];
            String socket = split[1];
            host = "http://" + ip + ":" + socket + "/";
            return host;
        }
        return host;
    }
//    .header(UrlConts.PAGEINDEX, "pageIndex").header(UrlConts.CATALOG, "catalog").header(UrlConts.PAGESIZE, "pageSize").
    /**
     * 主机
     */
    public static String HOST = makeHost();
    public static String MESSAGE_URL = HOST + "oschina/list/news";
}
