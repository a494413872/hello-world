package com.lvmama.util;

public class StringUtil {
    public static boolean isNotEmpty(String words) {
        return !isEmpty(words);
    }
    public static boolean isEmpty(String words) {
        if(words==null||"".equals(words)){
            return true;
        }
        return false;
    }
}
