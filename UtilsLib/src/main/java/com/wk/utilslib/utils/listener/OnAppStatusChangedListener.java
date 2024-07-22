package com.wk.utilslib.utils.listener;

import android.app.Activity;

public interface OnAppStatusChangedListener {
    void onForeground(Activity activity);
    void onBackground(Activity activity);
}
