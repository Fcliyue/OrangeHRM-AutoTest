package com.orangehrm.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LoggerUtils {
    public static Logger getLogger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
    public static Logger getLogger(Class<?> clazz){
        return LogManager.getLogger(clazz);
    }
}
