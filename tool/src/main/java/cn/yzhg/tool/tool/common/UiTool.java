package cn.yzhg.tool.tool.common;

import android.graphics.drawable.Drawable;
import android.os.Build;

import java.text.DecimalFormat;

import cn.yzhg.tool.MyTool;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class UiTool {

    /*
     * 获取Xml字符串资源
     * @param resId
     * @return
     */
    public static String getString(int resId){
        return MyTool.getContext().getResources().getString(resId);
    }

    /*
     * 获取字符串数组
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return MyTool.getContext().getResources().getStringArray(resId);
    }
     /*
     * 获取图片资源
     * @param resId
     * @return
     */
    public static Drawable getDrawable(int resId) {
        return MyTool.getContext().getResources().getDrawable(resId);
    }

     /*
     * 获取颜色
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return MyTool.getContext().getResources().getColor(resId);
    }

    /*
     * 获取尺寸
     * @param resId
     * @return
     */
    public static int getDimen(int resId) {
        return MyTool.getContext().getResources().getDimensionPixelSize(resId);
    }


    /**
     * dp与px之间的转换
     *
     * @param dip
     * @return
     */
    public static int dip2px(float dip) {
        //获取屏幕的密度
        float density = MyTool.getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public static float px2dip(float px) {
        float density = MyTool.getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    /**
     * Long类型,保留两位小数
     * @param money
     * @return
     */
    public static String saveDouble(long money) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(money);
    }

    /*
     * double类型,保留两位小数
     * @param money
     * @return
     */
    public static String saveDoubleNUmber(double money) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(money);
    }

    /*
     * 判断SDK版本
     *
     * @param SDKVersion
     * @return
     */
    public static boolean jumpSDK(int SDKVersion) {
        return Build.VERSION.SDK_INT >= SDKVersion;
    }
}
