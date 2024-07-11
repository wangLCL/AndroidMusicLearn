package com.wk.utilslib.utils;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.Lifecycle;

public final class Utils {
    private static Application sApp;
    private Utils(){
        throw new UnsupportedOperationException("can't new instane");
    }

    public static void init(final Application app){
        if (app == null){
            Log.e("utils","app is null");
            return;
        }
        if (sApp == null){
            sApp = app;
        }
    }

    public interface OnAppStatusChangeListener{
        void onForeground(Activity activity);

        void onBackground(Activity activity);
    }

    public static class ActivityLifecleCallbacks{
        public void onActivityCreated(Activity activity){}
        public void onActivityStared(Activity activity){}
        public void onActivityResumed(Activity activity) {/**/}
        public void onActivityPaused(Activity activity) {/**/}
        public void onActivityStopped(Activity activity) {/**/}
        public void onActivityDestroyed(Activity activity) {/**/}
        public void onLifecycleChanged(Activity activity, Lifecycle.Event event) {/**/}
    }

    public interface Consumer<T>{
        void accept(T t);
    }


    public interface Supplier<T> {
        T get();
    }

    public interface Func1<Ret, Par> {
        Ret call(Par param);
    }
}
