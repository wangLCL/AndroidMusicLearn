package com.wk.utilslib.utils.zip;

import com.wk.utilslib.utils.FileUtils;
import com.wk.utilslib.utils.string.StringUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.zip.ZipEntry;
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
                File fileByPath = FileUtils.getFileByPath(srcFile);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private static boolean zipFile(
            final File srcFile,
            String rootPath,
            final ZipOutputStream zos,
            final String comment
    ) throws IOException {
        rootPath = rootPath + (StringUtils.isSpace(rootPath)?"" : File.separator) + srcFile.getName();
        if (srcFile.isDirectory()){
            File[] fileList = srcFile.listFiles();
            if (fileList!=null || fileList.length<=0) {
                ZipEntry entry = new ZipEntry(rootPath+"/");
                entry.setComment(comment);
                zos.putNextEntry(entry);
                zos.close();
            }else {
                for (File file : fileList) {
                    if (!zipFile(file,rootPath,zos,comment))return false;
                }
            }
        }else {
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(srcFile));
                ZipEntry entry = new ZipEntry(rootPath);
                entry.setComment(comment);
                zos.putNextEntry(entry);
                byte buffer[] = new byte[BUFFER_LEN];
                int len;
                while ((len = is.read(buffer, 0, BUFFER_LEN)) != -1) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
        return true;
    }

}
