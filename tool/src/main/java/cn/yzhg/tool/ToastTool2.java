package cn.yzhg.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * author yzhg , time 2018/1/27.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class ToastTool2 {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
