package com.wk.utilslib.utils.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityUtils implements Application.ActivityLifecycleCallbacks {
    private Activity activity = new Activity();
    private HashMap<Activity, ArrayList<ActivityLifecycleCallBack>> activityArrayListHashMap;
    public void addActivityLifeCycleCallBack(ActivityLifecycleCallBack activityLifecycleCallBack){
        if (activityArrayListHashMap == null){
            activityArrayListHashMap = new HashMap<>();
        }
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        if (activityLifecycleCallBacks == null){
            activityLifecycleCallBacks = new ArrayList<>();
            activityArrayListHashMap.put(activity,activityLifecycleCallBacks);
        }
        activityLifecycleCallBacks.add(activityLifecycleCallBack);
    }

    public void removeActivityLifeCycleCallBack(ActivityLifecycleCallBack activityLifecycleCallBack){
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        activityLifecycleCallBacks.remove(activityLifecycleCallBack);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        if (activityLifecycleCallBacks!=null) {
            for (ActivityLifecycleCallBack activityLifecycleCallBack : activityLifecycleCallBacks) {
                activityLifecycleCallBack.onActivityCreated(activity);
            }
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        if (activityLifecycleCallBacks!=null) {
            for (ActivityLifecycleCallBack activityLifecycleCallBack : activityLifecycleCallBacks) {
                activityLifecycleCallBack.onActivityStarted(activity);
            }
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        if (activityLifecycleCallBacks!=null) {
            for (ActivityLifecycleCallBack activityLifecycleCallBack : activityLifecycleCallBacks) {
                activityLifecycleCallBack.onActivityResumed(activity);
            }
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        if (activityLifecycleCallBacks!=null) {
            for (ActivityLifecycleCallBack activityLifecycleCallBack : activityLifecycleCallBacks) {
                activityLifecycleCallBack.onActivityPaused(activity);
            }
        }

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        if (activityLifecycleCallBacks!=null) {
            for (ActivityLifecycleCallBack activityLifecycleCallBack : activityLifecycleCallBacks) {
                activityLifecycleCallBack.onActivityStopped(activity);
            }
        }

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {


    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        ArrayList<ActivityLifecycleCallBack> activityLifecycleCallBacks = activityArrayListHashMap.get(activity);
        if (activityLifecycleCallBacks!=null) {
            for (ActivityLifecycleCallBack activityLifecycleCallBack : activityLifecycleCallBacks) {
                activityLifecycleCallBack.onActivityDestroyed(activity);
            }
        }
    }
}
