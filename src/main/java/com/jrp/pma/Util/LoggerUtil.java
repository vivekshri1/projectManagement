package com.jrp.pma.util;

public  class LoggerUtil {
    public static void log(String msg){
        System.out.println(Thread.currentThread()+" "+msg);
    }
}
