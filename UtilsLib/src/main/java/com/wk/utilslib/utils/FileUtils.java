package com.wk.utilslib.utils;

import android.os.Build;
import android.os.Handler;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;

public class FileUtils {
    private static final String LINE_SEP = File.separator;
    public static File getFileByPath(final String filePath){
        StringUtils
    }



    public static long getFsTotalSize(String anyPathInFs){
        if (TextUtils.isEmpty(anyPathInFs))return 0;
        StatFs statFs = new StatFs(anyPathInFs);
        long blockSize;
        long totalSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            blockSize = statFs.getBlockSizeLong();
            totalSize = statFs.getBlockCountLong();
        }else {
            blockSize = statFs.getBlockSize();
            totalSize = statFs.getBlockCount();
        }
        return blockSize * totalSize;
    }

    public static long getFsAcailableSize(final String anyPathInFs){
        if (TextUtils.isEmpty(anyPathInFs))return 0;
        StatFs statFs = new StatFs(anyPathInFs);
        long blockSize ;
        long availableSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            blockSize = statFs.getBlockSizeLong();
            availableSize = statFs.getAvailableBlocksLong();
        }else {
            blockSize = statFs.getBlockSize();
            availableSize = statFs.getAvailableBytes();
        }
        return blockSize * availableSize;
    }

    public interface OnReplaceListener{
        boolean onReplace(File srcFile,File destFile);
    }
}
