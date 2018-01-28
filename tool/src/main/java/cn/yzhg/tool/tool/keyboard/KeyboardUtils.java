package cn.yzhg.tool.tool.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class KeyboardUtils {
    /**
     * 某个特定view获得焦点时，关闭软键盘
     *
     * @param context view所在activity
     * @param view    当前activity中获取焦点的view
     */
    public static void closeKeyboardForCommonAct(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) (context).getSystemService(INPUT_METHOD_SERVICE);
        if (((Activity) context).getCurrentFocus().getWindowToken() != null) {
            imm.hideSoftInputFromInputMethod(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 普通关闭
     *
     * @param context
     */
    public static void closeKeyboardCommAct(Context context) {
        InputMethodManager imm = (InputMethodManager) (context).getSystemService(INPUT_METHOD_SERVICE);
        if (((Activity) context).getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    /**
     * 进入画面时，关闭键盘
     *
     * @param context
     */
    public static void closeWhenOnCreate(Context context) {
        // SOFT_INPUT_STATE_ALWAYS_VISIBLE  键盘始终显示
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    //此方法，如果显示则隐藏，如果隐藏则显示
    private void hintKbOne(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        assert imm != null;
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
