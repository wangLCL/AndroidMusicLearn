package com.wk.utilslib.utils.adaptscreen;

import android.content.res.Resources;

import java.lang.reflect.Field;
import java.util.List;

public class AdaptScreenUtils {
    private static List<Field> sMetricsFields;
    private AdaptScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Resources adaptWidth(final Resources resources, final int designWidth) {
        float newXdpi = (resources.getDisplayMetrics().widthPixels * 72f) / designWidth;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }

    public static Resources adaptHeight(final Resources resources, final int designHeight) {
        return adaptHeight(resources, designHeight, false);
    }

    private static void applyDisplayMetrics(final Resources resources, final float newXdpi) {
        resources.getDisplayMetrics().xdpi = newXdpi;
        Utils.getApp().getResources().getDisplayMetrics().xdpi = newXdpi;
        applyOtherDisplayMetrics(resources, newXdpi);
    }
}
