package com.wk.utilslib.utils.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public final class ThreadUtils {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final Map<Integer,Map<Integer, ExecutorService>> TYPE_PRIORITY_POOLS = new HashMap<>();
//    private static final Map<Task,ExecutorService> TASK_POOL_MAP = new ConcurrentHashMap<>();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Timer TIMER = new Timer();
    private static final byte TYPE_SINGLE = -1;

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        }else {
            HANDLER.post(runnable);
        }
    }
}
