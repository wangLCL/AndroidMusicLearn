package com.wk.learn.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
//                System.out.println("-=--------------------------");
//            }
//
//            @Override
//            public void onActivityStarted(@NonNull Activity activity) {
//                System.out.println("-=--------------------------");
//            }
//
//            @Override
//            public void onActivityResumed(@NonNull Activity activity) {
//                System.out.println("-=--------------------------");
//            }
//
//            @Override
//            public void onActivityPaused(@NonNull Activity activity) {
//                System.out.println("-=--------------------------");
//            }
//
//            @Override
//            public void onActivityStopped(@NonNull Activity activity) {
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(@NonNull Activity activity) {
//
//            }
//        });
    }
}
