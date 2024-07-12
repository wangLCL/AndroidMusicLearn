package com.wk.utilslib.utils.zip;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private static final int BUFFER_LEN = 8192;

    private ZipUtils(){
        throw new UnsupportedOperationException("can't instance ");
    }

    public static boolean zipFiles(
            final Collection<String> srcFiles,
            final String zipFilePath
            ){
        return zipFiles(srcFiles,zipFilePath,null);
    }

    private static boolean zipFiles(
            final Collection<String> srcFiles,
            final String zipFilePath,
            final String comment) {
        if (srcFiles == null || zipFilePath == null){
            return false;
        }
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
            for (String srcFile : srcFiles) {

            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
