package com.lerrycr.oschina.utils;

import android.util.Log;

/**
 * Created by hasee on 2016/10/13.
 */

public class Logger {
    private static boolean isOpen = true;

    /**
     * @param objTag
     * @param objMsg
     */
    public static void i(Object objTag, Object objMsg) {
        if (isOpen) {
            String tag = (String) tag2String(objTag);
            String msg = (String) msg2String(objMsg);
            Log.i(tag, msg);
        }
    }

    /**
     * 将打印内容转换为String类型
     *
     * @param objMsg
     * @return
     */
    public static CharSequence msg2String(Object objMsg) {
        if (objMsg == null) {
            return "nullTag";
        } else if (objMsg instanceof Class) {
            return ((Class) objMsg).getSimpleName();
        } else {
            return objMsg.toString();
        }
    }

    /**
     * 将标签转换为string类型
     *
     * @param objTag
     * @return
     */
    public static CharSequence tag2String(Object objTag) {
        /**
         * 判断拿到的是不是Class
         */
        if (objTag instanceof Class) {
            return ((Class) objTag).getSimpleName();
        } else if (objTag instanceof String) {
            return (String) objTag;
        } else {
            return objTag.getClass().getSimpleName();
        }
    }
}
