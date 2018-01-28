package cn.yzhg.tool;

import android.content.Context;

import cn.yzhg.tool.constant.ToolConstant;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class MyTool {

    private static Context context;

    private MyTool() {
        throw new UnsupportedOperationException("不能够初始化该工具类");
    }

    /*
     * 初始化工具类操作,用于获取上下文对象
     *
     * 在Application中进行初始化
     * @param context
     */
    public static void initTool(Context context) {
        MyTool.context = context.getApplicationContext();
    }

    /*
     * 初始化日志打印工具,在Application中
     * 默认为false不进行日志打印
     * @param isOpenBug
     *
     * @param tagName
     *    日志的tagName  如果设置为null,就默认为 ------myTool------
     *    否则,设置为tagName
     */
    public static void initLog(boolean isOpenLog, String tagName) {
        ToolConstant.isOpenLog = isOpenLog;
        ToolConstant.tagName = (tagName == null) ? "------myTool------" : tagName;
    }

    public static Context getContext() {
        if (context != null) return context;
        throw new UnsupportedOperationException("必须先在Application中初始化该工具,示例:MyTool.initTool(上下文)");
    }
}
