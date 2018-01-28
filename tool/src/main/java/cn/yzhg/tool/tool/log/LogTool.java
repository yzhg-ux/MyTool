package cn.yzhg.tool.tool.log;

import android.util.Log;

import cn.yzhg.tool.constant.ToolConstant;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(日志打印工具)
 *
 * 每个日志工具都有一对
 *        两个参数的方法,可以自己指定tagName
 *        一个参数的方法,使用统一的tagName.可以是默认的 也可以在Application中统一指定
 */

public class LogTool {

    public static void v(String TAG, String msg) {
        if (ToolConstant.isOpenLog) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (ToolConstant.isOpenLog) {
            Log.v(ToolConstant.tagName, msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (ToolConstant.isOpenLog) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (ToolConstant.isOpenLog) {
            Log.d(ToolConstant.tagName, msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (ToolConstant.isOpenLog) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (ToolConstant.isOpenLog) {
            Log.i(ToolConstant.tagName, msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (ToolConstant.isOpenLog) {
            Log.w(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (ToolConstant.isOpenLog) {
            Log.w(ToolConstant.tagName, msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (ToolConstant.isOpenLog) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (ToolConstant.isOpenLog) {
            Log.e(ToolConstant.tagName, msg);
        }
    }

    public static void wtf(String TAG, String msg) {
        if (ToolConstant.isOpenLog) {
            Log.wtf(TAG, msg);
        }
    }

    public static void wtf(String msg) {
        if (ToolConstant.isOpenLog) {
            Log.wtf(ToolConstant.tagName, msg);
        }
    }
}
