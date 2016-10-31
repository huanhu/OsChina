package com.lerrycr.oschina.utils;

import android.content.Context;

import com.lerrycr.oschina.MyApp;

/**
 * Created by Lerry on 2016/10/31.
 */

public class ThemeSwitchUtils {
    public static void switchTheme(Context context) {

    }
    public static String getWebViewBodyString() {
        if (MyApp.getNightModeSwitch()) {
            return "<body class='night'><div class='contentstyle' id='article_body'>";
        } else {
            return "<body style='background-color: #FFF'><div class='contentstyle' id='article_body' >";
        }
    }
}
