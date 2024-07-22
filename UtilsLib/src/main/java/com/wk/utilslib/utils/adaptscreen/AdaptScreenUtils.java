package com.wk.utilslib.utils.adaptscreen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wk.utilslib.utils.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 像素密度
 *宽平方+高平方 开方
 *
 *除以 寸尺 / 160 =》 density
 *
 * ================================
 * 屏幕宽度   设计尺寸
 */
public class AdaptScreenUtils {
    private static List<Field> sMetricsFields;
    private AdaptScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 在 Android 开发中，xdpi 和 widthPixels 是两个不同的概念，分别用于描述屏幕的像素密度和屏幕的宽度。
     *
     * xdpi（X轴方向的像素密度）：
     *
     * xdpi 表示屏幕在水平（X 轴）方向上的像素密度，即每英寸包含的像素数。它是一个浮点数值，单位为 dpi（dots per inch，每英寸像素数）。
     * 例如，如果设备的 xdpi 为 320，则表示每英寸水平方向上有 320 个像素。这个值通常用于确定设备屏幕在物理尺寸上的像素密度，以及在绘制 UI 元素时的像素转换。
     * widthPixels（屏幕宽度的像素数）：
     *
     * widthPixels 表示屏幕在水平方向上的物理像素宽度，即屏幕的实际分辨率宽度。
     * 例如，如果 widthPixels 返回 1920，则表示屏幕的水平分辨率为 1920 像素。
     * 这个值通常用于布局计算、绘制内容和确定屏幕可视区域的大小。
     * 区别总结：
     *
     * xdpi 是一个测量单位，表示屏幕在水平方向上每英寸的像素数量，用于计算屏幕的物理像素密度。
     * widthPixels 是一个具体的数值，表示屏幕在水平方向上的像素宽度，用于确定屏幕的实际分辨率大小。
     * 在开发中，你可以根据这两个值来优化应用程序的布局和渲染，以适应不同的屏幕尺寸和分辨率。
     *
     *
     * @param resources
     * @param designWidth
     * @return
     */
//    72 DPI 是打印领域中常见的标准，因为在打印术语中，1 英寸被定义为 72 点。
//    使用 Resources.getDisplayMetrics().widthPixels 获取的是设备屏幕的物理像素宽度

    /**
     * 尝试理解一下：
     *
     * 设计尺寸720  1280  分辨率72
     *
     * 屏幕尺寸720 1280  我不需要缩放   一英寸里面的大小是72
     *
     *
     * density = dpi / 160;
     *
     * 如果我们修改了dpi,结果就会发生改变
     *
     * 适配可以直接通过修改dpi
     *
     * dp * dpi / 160
     * @param resources
     * @param designWidth
     * @return
     */
    public static Resources adaptWidth(final Resources resources, final int designWidth) {
        float newXdpi = (resources.getDisplayMetrics().widthPixels * 72f) / designWidth;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }

    public static Resources adaptHeight(final Resources resources, final int designHeight) {
        return adaptHeight(resources, designHeight, false);
    }

    public static Resources adaptHeight(final Resources resources, final int designHeight, final boolean includeNavBar) {
        float screenHeight = (resources.getDisplayMetrics().heightPixels
                + (includeNavBar ? getNavBarHeight(resources) : 0)) * 72f;
        //分辨率
        float newXdpi = screenHeight / designHeight;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }


    private static int getNavBarHeight(final Resources resources) {
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    public static Resources closeAdapt(final Resources resources) {
        float newXdpi = Resources.getSystem().getDisplayMetrics().density * 72f;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }

    public static int pt2Px(final float ptValue) {
        DisplayMetrics metrics = Utils.getsApp().getResources().getDisplayMetrics();
        //72.像素
        return (int) (ptValue * metrics.xdpi / 72f + 0.5);
    }

    public static int px2Pt(final float pxValue) {
        DisplayMetrics metrics = Utils.getsApp().getResources().getDisplayMetrics();
        return (int) (pxValue * 72 / metrics.xdpi + 0.5);
    }

    private static void applyDisplayMetrics(final Resources resources, final float newXdpi) {
        resources.getDisplayMetrics().xdpi = newXdpi;
        Utils.getsApp().getResources().getDisplayMetrics().xdpi = newXdpi;
        applyOtherDisplayMetrics(resources, newXdpi);
    }

    static Runnable getPreLoadRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                preLoad();
            }
        };
    }

    private static void preLoad() {
        applyDisplayMetrics(Resources.getSystem(), Resources.getSystem().getDisplayMetrics().xdpi);
    }


    private static void applyOtherDisplayMetrics(final Resources resources, final float newXdpi) {
        if (sMetricsFields == null) {
            sMetricsFields = new ArrayList<>();
            Class resCls = resources.getClass();
            Field[] declaredFields = resCls.getDeclaredFields();
            while (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.getType().isAssignableFrom(DisplayMetrics.class)) {
                        field.setAccessible(true);
                        DisplayMetrics tmpDm = getMetricsFromField(resources, field);
                        if (tmpDm != null) {
                            sMetricsFields.add(field);
                            tmpDm.xdpi = newXdpi;
                        }
                    }
                }
                resCls = resCls.getSuperclass();
                if (resCls != null) {
                    declaredFields = resCls.getDeclaredFields();
                } else {
                    break;
                }
            }
        } else {
            applyMetricsFields(resources, newXdpi);
        }
    }


    private static DisplayMetrics getMetricsFromField(final Resources resources, final Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception ignore) {
            return null;
        }
    }

    private static void applyMetricsFields(final Resources resources, final float newXdpi) {
        for (Field metricsField : sMetricsFields) {
            try {
                DisplayMetrics dm = (DisplayMetrics) metricsField.get(resources);
                if (dm != null) dm.xdpi = newXdpi;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
