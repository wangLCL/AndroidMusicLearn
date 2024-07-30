package com.wk.utilslib.utils.bar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.view.WindowInsetsControllerCompat;

import com.wk.utilslib.utils.Utils;

public class BarUtils {
    private static final String TAG_STATUS_BAR = "TAG_STATUS_BAR";
    private static final String TAG_OFFSET = "TAG_OFFSET";
    private static final int KEY_OFFSET = -123;

    /**
     * 获取状态栏的高度
     * @return
     */
    public static int getStatusBarHeight(){
        Resources resources = Utils.getsApp().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 显示 隐藏状态栏
     * @param activity
     * @param isVisible
     */
    public static void setStatusBarVisibility(
            final Activity activity,
            final boolean isVisible
            ){
        setStatusBarVisibility(activity.getWindow(),isVisible);
    }

    public static void setStatusBarVisibility(
            final Window window,
            final boolean isVisible
            ){
        if (isVisible){
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            showStatusBarView(window);
            addMarginTopEqualStatusBarHeight(window);
        }else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            hideStatusBarView(window);

        }
    }

    private static void addMarginTopEqualStatusBarHeight(Window window){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)return;
        View viewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET);
        if (viewWithTag == null)return;
        addMarginTopEqualStatusBarHeight(viewWithTag);

    }

    private static void addMarginTopEqualStatusBarHeight(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)return;
        view.setTag(TAG_OFFSET);
        Object haveSetOffset = view.getTag(KEY_OFFSET);
        if (haveSetOffset != null && (Boolean) haveSetOffset) return;
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin,
                layoutParams.topMargin + getStatusBarHeight(),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
        view.setTag(KEY_OFFSET, true);
    }

    private static void hideStatusBarView(final Window window) {
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        View fakeStatusBarView = decorView.findViewWithTag(TAG_STATUS_BAR);
        if (fakeStatusBarView == null) return;
        fakeStatusBarView.setVisibility(View.GONE);
    }

    private static void showStatusBarView(
            Window window
    ){
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        View viewWithTag = decorView.findViewWithTag(TAG_STATUS_BAR);
        if (viewWithTag==null)return;
        viewWithTag.setVisibility(View.VISIBLE);
    }

//    public static boolean isNavBarVisible(
//            Window window
//    ){
//
//    }

    public static String getResNameById(int id){
        try {
           return Utils.getsApp().getResources().getResourceEntryName(id);
        }catch (Exception e){
            return "";
        }
    }

    public static void setNavBarColor(final Window window,
                                      final int color){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(color);
    }

    public static int getNavBarColor(final Window window){
        return window.getNavigationBarColor();
    }

    public static boolean isSupportNavBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            WindowManager windowManager = (WindowManager) Utils.getsApp().getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null)return false;
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            defaultDisplay.getSize(size);
            defaultDisplay.getRealSize(realSize);
            return realSize.x != size.x || realSize.y != size.y;
        }
        boolean isMenu = ViewConfiguration.get(Utils.getsApp()).hasPermanentMenuKey();
        boolean isBack = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        return !isMenu && !isBack;
    }


    public static void setNavBarLightMode(
            Window window,
            boolean isLightMode
    ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            WindowInsetsControllerCompat controllerCompat = WindowInsetsControllerCompat.toWindowInsetsControllerCompat(window.getInsetsController());
            controllerCompat.setAppearanceLightNavigationBars(true);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            if (isLightMode){
                systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }else {
                systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(systemUiVisibility);
        }
    }
    /**
     * Is the nav bar light mode.
     *
     * @param window The window.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNavBarLightMode(@NonNull final Window window) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            WindowInsetsControllerCompat insetsController = WindowInsetsControllerCompat.toWindowInsetsControllerCompat(window.getInsetsController());
            if (insetsController!=null){
                boolean isNavBarLightMode = insetsController.isAppearanceLightNavigationBars();
            }
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            View decorView = window.getDecorView();
            int vis = decorView.getSystemUiVisibility();
            return (vis & View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR) != 0;

        }
        return false;
    }
}
