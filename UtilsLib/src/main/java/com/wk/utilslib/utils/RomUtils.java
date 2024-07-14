package com.wk.utilslib.utils;

import java.lang.reflect.Method;

public class RomUtils {
    public static String getSystemPropertyByReflect(String key){
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz,key,"");
        }catch (Exception e){

        }
        return "";
    }

    private static class RomInfo{
        private String name;
        private String version;

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "RomInfo{" +
                    "name='" + name + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }
}
