package com.jrp.pma.Util;

public  class LoggerUtil {
    public static void log(String msg){
        System.out.println(Thread.currentThread()+" "+msg);
    }
}
