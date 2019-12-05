package com.thread.thrackNumber;

public class FawneLog {
    public static void logInfo(String str){

        Log4JLog.logInfo("["+LogTrackContext.getTrackNumber()+"]"+str);
    }
}
