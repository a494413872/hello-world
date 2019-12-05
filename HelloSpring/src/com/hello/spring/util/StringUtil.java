package com.hello.spring.util;

public class StringUtil {
    public static String lowerFirstChar(String str){
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
    public static boolean isEmptyString(String str){
        if(str!=null&&!"".equals(str)){
            return false;
        }
        return true;
    }
}
