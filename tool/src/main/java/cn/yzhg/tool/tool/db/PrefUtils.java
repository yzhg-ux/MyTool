package cn.yzhg.tool.tool.db;

import android.content.Context;
import android.content.SharedPreferences;

import cn.yzhg.tool.MyTool;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class PrefUtils {
    private static final String SHARE_PREFS_NAME = "config";

    public static void putBoolean(String prefsName,String key, boolean value) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        pref.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String prefsName,String key, boolean defaultValue) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    public static void putString(String prefsName,String key, String value) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public static String getString(String prefsName,String key, String defaultValue) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

    public static void putInt(String prefsName,String key, int value) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        pref.edit().putInt(key, value).apply();
    }

    public static int getInt(String prefsName,String key, int defaultValue) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    public static void putLong(String prefsName,String key, long value) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        pref.edit().putLong(key, value).apply();
    }

    public static long getLong(String prefsName,String key, long defaultValue) {
        SharedPreferences pref = MyTool.getContext().getSharedPreferences(prefsName,
                Context.MODE_PRIVATE);
        return pref.getLong(key, defaultValue);
    }

    /*清空数据*/
    public static boolean clearNumber(String prefsName) {
        SharedPreferences clearSp = MyTool.getContext().getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return clearSp != null && clearSp.edit().clear().commit();
    }
}
