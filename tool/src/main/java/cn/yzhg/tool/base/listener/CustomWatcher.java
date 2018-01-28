package cn.yzhg.tool.base.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class CustomWatcher implements TextWatcher {
    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
}