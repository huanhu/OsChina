package com.lerrycr.oschina.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    public static SharedPreferences sp;

    public static SharedPreferences getSharedPreferences(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp;
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        getSharedPreferences(context);
        return sp.getBoolean(key, defValue);
    }

    public static boolean putInt(Context context, String key, int value) {
        getSharedPreferences(context);
        boolean commit = sp.edit().putInt(key, value).commit();
        return commit;
    }

    public static int getInt(Context context, String key, int defValue) {
        getSharedPreferences(context);
        return sp.getInt(key, defValue);
    }

    public static boolean putString(Context context, String key, String value) {
        getSharedPreferences(context);
        boolean commit = sp.edit().putString(key, value).commit();
        return commit;
    }

    public static String getString(Context context, String key, String defValue) {
        getSharedPreferences(context);
        return sp.getString(key, defValue);
    }

    /**
     * 移除一个key
     *  @param context
     * @param key
     */
    public static boolean remove(Context context, String key) {
        getSharedPreferences(context);
        boolean commit = sp.edit().remove(key).commit();
        return commit;
    }

}
