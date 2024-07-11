package com.wk.utilslib.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class UtilsActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
    static final UtilsActivityLifecycleImpl INSTANCE = new UtilsActivityLifecycleImpl();
    private final LinkedList<Activity> mActivityList = new LinkedList<>();
    private final List<Utils.OnAppStatusChangeListener> statusChangeListeners = new CopyOnWriteArrayList<>();
    private final Map<Activity,List<Utils.ActivityLifecleCallbacks>> activityListMap = new ConcurrentHashMap<>();
    private static final Activity STUB = new Activity();
    private int mForegroundCount = 0;
    private int mConfigCount = 0;
    private boolean mIsBackground = false;

    void init(Application app){
        app.registerActivityLifecycleCallbacks(this);
    }

    void unInit(Application application){
        mActivityList.clear();
        application.unregisterActivityLifecycleCallbacks(this);
    }

    Activity getTopActivity(){
        List<Activity> activities = getTopActivity()
    }

    private List<Activity> getActivitiesByReflect(){
        LinkedList<Activity> list = new LinkedList<>();
        Activity topActivity = null;
        try {
            getAc
        }catch (Exception e){

        }
    }

    private Object getActivityThread(){
        Object activityThread = getActivityThreadInActivityThreadStaticField();
    }

    private Object getActivityThreadInActivityThreadStaticField(){
        Class<?> activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("");
            Field declaredField = activityThreadClass.getDeclaredField("");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
