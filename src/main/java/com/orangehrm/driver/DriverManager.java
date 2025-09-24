package com.orangehrm.driver;

import com.orangehrm.utils.ConfUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    //ThreadLocal:为每一个线程创建独立的Driver instance
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    //获取当前线程的Driver instance
    public static void initDriver(){
        WebDriver driver;
        String browserType = ConfUtils.getProperty("browser.type");
        switch (browserType.toLowerCase()){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new IllegalArgumentException("不支持的浏览器类型"+browserType);
        }
        driverThreadLocal.set(driver);
    }

    //
    public static WebDriver getDriver(){
        if(driverThreadLocal.get()==null){
            initDriver();
        }
        return driverThreadLocal.get();
    }
    //关闭当前线程的Driver instance, 并移除ThreadLocal中的引用
    public static void quitDriver(){
        WebDriver driver = driverThreadLocal.get();
        if (driver !=null){
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
