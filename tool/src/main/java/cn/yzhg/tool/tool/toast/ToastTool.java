package cn.yzhg.tool.tool.toast;

import android.annotation.SuppressLint;
import android.widget.Toast;

import cn.yzhg.tool.MyTool;

/**
 * author yzhg , time 2018/1/27.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class ToastTool {
    private static Toast sToast;

    public static void showToast(int resId, int time) {
        show(MyTool.getContext().getString(resId), time);
    }

    public static void showToast(String msg, int time) {
        show(msg, time);
    }

    @SuppressLint("ShowToast")
    private static void show(String msg, int lengthShort) {
        if (sToast == null) {
            sToast = Toast.makeText(MyTool.getContext(), msg, lengthShort);
        }
        sToast.setText(msg);
        sToast.show();
    }


}
