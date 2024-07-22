package com.wk.utilslib.utils.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wk.utilslib.utils.listener.ActivityLifecleCallbacks;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityUtils implements Application.ActivityLifecycleCallbacks {
    private Activity tempActivity = new Activity();
    private HashMap<Activity, ArrayList<ActivityLifecleCallbacks>> activityArrayListHashMap;
    public void addActivityLifecleCallbacks(ActivityLifecleCallbacks ActivityLifecleCallbacks){
        if (activityArrayListHashMap == null){
            activityArrayListHashMap = new HashMap<>();
        }
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(tempActivity);
        if (ActivityLifecleCallbackss == null){
            ActivityLifecleCallbackss = new ArrayList<>();
            activityArrayListHashMap.put(tempActivity,ActivityLifecleCallbackss);
        }
        ActivityLifecleCallbackss.add(ActivityLifecleCallbacks);
    }

    public void removeActivityLifecleCallbacks(ActivityLifecleCallbacks ActivityLifecleCallbacks){
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(tempActivity);
        ActivityLifecleCallbackss.remove(ActivityLifecleCallbacks);
    }

    /**
     * 获取活跃的Activity
     * @param context
     * @return
     */
    public Activity getActivityByContext(Context context) {
        Activity activity = getActivityByContextInner(context);
        if (!isActivityAlive(activity)) return null;
        return activity;
    }

    public static boolean isActivityAlive(final Activity activity) {
        return activity != null
                && !activity.isFinishing()
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !activity.isDestroyed());
    }

    public Activity getActivityByContextInner(Context context){
        if (context == null)return null;
        List<Context> list = new ArrayList<>();
        while (context instanceof ContextWrapper){
            if (context instanceof Activity){
                return (Activity) context;
            }
            Activity activityFromDecorContext = getActivityFromDecorContext(context);
            if (activityFromDecorContext!=null){
                return activityFromDecorContext;
            }
            list.add(context);
            context = ((ContextWrapper)context).getBaseContext();
            if (context == null){
                return null;
            }
            if (list.contains(context)){
                return null;
            }
        }
        return null;
    }

    private static Activity getActivityFromDecorContext(Context context) {
        if (context == null) return null;
        if (context.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
            try {
                Field mActivityContextField = context.getClass().getDeclaredField("mActivityContext");
                mActivityContextField.setAccessible(true);
                //noinspection ConstantConditions,unchecked
                return ((WeakReference<Activity>) mActivityContextField.get(context)).get();
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(activity);
        if (ActivityLifecleCallbackss!=null) {
            for (ActivityLifecleCallbacks ActivityLifecleCallbacks : ActivityLifecleCallbackss) {
                ActivityLifecleCallbacks.onActivityCreated(activity);
            }
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(activity);
        if (ActivityLifecleCallbackss!=null) {
            for (ActivityLifecleCallbacks ActivityLifecleCallbacks : ActivityLifecleCallbackss) {
                ActivityLifecleCallbacks.onActivityStared(activity);
            }
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(activity);
        if (ActivityLifecleCallbackss!=null) {
            for (ActivityLifecleCallbacks ActivityLifecleCallbacks : ActivityLifecleCallbackss) {
                ActivityLifecleCallbacks.onActivityResumed(activity);
            }
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(activity);
        if (ActivityLifecleCallbackss!=null) {
            for (ActivityLifecleCallbacks ActivityLifecleCallbacks : ActivityLifecleCallbackss) {
                ActivityLifecleCallbacks.onActivityPaused(activity);
            }
        }

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(activity);
        if (ActivityLifecleCallbackss!=null) {
            for (ActivityLifecleCallbacks ActivityLifecleCallbacks : ActivityLifecleCallbackss) {
                ActivityLifecleCallbacks.onActivityStopped(activity);
            }
        }

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {


    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        ArrayList<ActivityLifecleCallbacks> ActivityLifecleCallbackss = activityArrayListHashMap.get(activity);
        if (ActivityLifecleCallbackss!=null) {
            for (ActivityLifecleCallbacks ActivityLifecleCallbacks : ActivityLifecleCallbackss) {
                ActivityLifecleCallbacks.onActivityDestroyed(activity);
            }
        }
    }
}
