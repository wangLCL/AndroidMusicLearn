package com.wk.utilslib.utils.file;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.StatFs;
import android.text.TextUtils;

import com.wk.utilslib.utils.Utils;
import com.wk.utilslib.utils.string.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class FileUtils {
    private static final String LINE_SEP = File.separator;
    public static File getFileByPath(final String filePath){
        return StringUtils.isSpace(filePath) ? null : new File(filePath);
    }

    public static long getFileLength(final File file){
        if (!isFile(file))return -1;
        return file.length();
    }

    public static boolean isFile(final File file){
        return file!= null && file.exists() && file.isFile();
    }

    public static byte[] getFileMd5(final String fileName){
        return getFileMD5(getFileByPath(fileName));
    }
    /**
     * 修改
     * @param file
     * @return
     */
    public static byte[] getFileMD5(final File file){
        if (file == null)return null;
        DigestInputStream dis = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            dis = new DigestInputStream(fis,md);
            byte[] buffer = new byte[1024 * 256];
            while (true){
                if (!(dis.read(buffer)>0))break;
            }
            md = dis.getMessageDigest();
            return md.digest();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (dis != null){
                    dis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

//    public static byte[] getFileMD51(final File file){
//        if (file == null)return null;
//
//        DigestInputStream dis = null;
//        try(
//                FileInputStream fis = new FileInputStream(file);
//        ){
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] buffer = new byte[1024 * 256];
//            while (true){
//                if (!(dis.read(buffer)>0))break;
//            }
//            md = dis.getMessageDigest();
//            return md.digest();
//
//        }catch (Exception e){
//
//        }finally {
//            try {
//                if (dis != null){
//                    dis.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }


    /**
     * 获取拓展名
     * @param file
     * @return
     */
    public static String getFileExtension(final File file){
        if (file == null)return "";
        return getFileExtension(file.getPath());
    }

    /**
     * 拓展名
     * @param filePath
     * @return
     */
    public static String getFileExtension(final String filePath) {
        if (StringUtils.isSpace(filePath)) return "";
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
    }


    public static void notifySystemToScan(final String filePath) {
        notifySystemToScan(getFileByPath(filePath));
    }

    //扫描文件系统   扫描之后会显示在文件系统中
    public static void notifySystemToScan(final File file){
        if (file == null || !file.exists())return;
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.parse("file://"+file.getAbsolutePath()));
        Utils.getsApp().sendBroadcast(intent);
    }

    //用于获取指定路径文件系统总可用空间的大小
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

//    用于获取指定路径文件系统可用空间的大小
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
