package com.lerrycr.oschina;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

/**
 * 应用程序在创建的时候先创建Application
 * Created by dzl on 2016/10/13.
 */

public class MyApp extends Application {

    private static Context context;

    public static ArrayList<Activity> getActivities() {
        return mActivities;
    }


    private static ArrayList<Activity> mActivities;

    /**
     * 获取Application类型的上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 当MyApp创建的时候这个方法会被调用
     */
    @Override
    public void onCreate() {
        context = this;
        //创建一个集合
        mActivities = new ArrayList<>();
    }

    //夜间模式
    public static boolean getNightModeSwitch() {
        //return getPreferences().getBoolean(KEY_NIGHT_MODE_SWITCH, false);
        return false;
    }


}
