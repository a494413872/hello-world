package com.thread.thrackNumber;

public class LogTrackContext {
    //自己去学习下ThreadLocal什么意思。不懂我再给你讲。
    static ThreadLocal<String> trackNumberContext = new ThreadLocal<>();

    public static void initTrackNumber() {
        trackNumberContext.set(getRandom18String());
    }

    public static String getTrackNumber() {
        return (String)trackNumberContext.get();
    }

    /**
     * 这个方法完全不需要关注
     * 只需要知道，它能生成完全随机的字符串就好了
     * @return
     */
    public static String getRandom18String() {
        Long millis = System.currentTimeMillis();
        Integer n = (int)(Math.random() * 100000.0D);
        int zeroNumber = 5 - n.toString().length();
        String pre = "";

        for(int i = 0; i < zeroNumber; ++i) {
            pre = pre + "0";
        }

        String r = millis.toString();
        if (!pre.equals("")) {
            r = r + pre + n.toString();
        } else {
            r = r + n.toString();
        }

        return Long.toHexString(Long.parseLong(r));
    }

}
