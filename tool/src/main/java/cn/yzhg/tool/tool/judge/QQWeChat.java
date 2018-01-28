package cn.yzhg.tool.tool.judge;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

import cn.yzhg.tool.MyTool;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(判断QQ和微信是否存在)
 *
 */
public class QQWeChat {
    public static boolean isWXAvailable() {
        final PackageManager packageManager = MyTool.getContext().getPackageManager();
        List<PackageInfo> pinFo = packageManager.getInstalledPackages(0);
        if (pinFo != null) {
            for (int i = 0; i < pinFo.size(); i++) {
                String pn = pinFo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @return
     */
    public static boolean isQQClientAvailable() {
        final PackageManager packageManager = MyTool.getContext().getPackageManager();
        List<PackageInfo> pinFo = packageManager.getInstalledPackages(0);
        if (pinFo != null) {
            for (int i = 0; i < pinFo.size(); i++) {
                String pn = pinFo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * 判断当前是否存在某个APP
     * @param appName APP包名
     * @return
     */
    public static boolean isExistAPP(String appName){
        final PackageManager packageManager = MyTool.getContext().getPackageManager();
        List<PackageInfo> pinFo = packageManager.getInstalledPackages(0);
        if (pinFo != null) {
            for (int i = 0; i < pinFo.size(); i++) {
                String pn = pinFo.get(i).packageName;
                if (pn.equals(appName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
