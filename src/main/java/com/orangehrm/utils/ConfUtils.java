package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfUtils {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Failed to load config.properties"+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        if(value==null || value.trim().isEmpty()){
            System.out.println("Property " + key + " is not found or empty in config.properties");
        }
        return value.trim();
    }
}
