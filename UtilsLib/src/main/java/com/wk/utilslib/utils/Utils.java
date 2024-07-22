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

    public static Application getsApp() {
        return sApp;
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
