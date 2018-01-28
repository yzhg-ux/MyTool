package cn.yzhg.tool.tool.common;

import android.content.pm.PackageInfo;

import cn.yzhg.tool.MyTool;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class PackageCode {

    /**
     * 获取当前版本号的方法
     *
     * @return
     */
    public static String getPackageVersionName() {
        try {
            PackageInfo packageInfo = MyTool.getContext().getPackageManager().getPackageInfo
                    (MyTool.getContext()
                    .getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取版本号失败";
        }
    }

    public static int getPackageVersionCode() {
        try {
            PackageInfo packageInfo = MyTool.getContext().getPackageManager().getPackageInfo
                    (MyTool.getContext().getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
