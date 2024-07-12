package com.wk.utilslib.utils.lifecycle;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

public abstract class ActivityLifecycleCallBack {

    public void onActivityCreated(@NonNull Activity activity) {/**/}

    public void onActivityStarted(@NonNull Activity activity) {/**/}

    public void onActivityResumed(@NonNull Activity activity) {/**/}

    public void onActivityPaused(@NonNull Activity activity) {/**/}

    public void onActivityStopped(@NonNull Activity activity) {/**/}

    public void onActivityDestroyed(@NonNull Activity activity) {/**/}

    public void onLifecycleChanged(@NonNull Activity activity, Lifecycle.Event event) {/**/}


}
