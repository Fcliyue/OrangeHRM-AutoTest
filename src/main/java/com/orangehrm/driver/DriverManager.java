package com.orangehrm.driver;

import com.orangehrm.utils.ConfUtils;
import com.orangehrm.utils.LocatorUtils;
import com.orangehrm.utils.LoggerUtils;
import io.cucumber.java.an.E;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.Logger;

public class DriverManager {
    /*知识点总结：
    1.final 保证引用不可变，代表全局唯一实例
    2.泛型：
    3.私有构造: 外部只能访问静态方法，不能new对象，也就是不能实例化
    */
    //1.线程安全：使用ThreadLocal 确保多线程下每个线程都有独立的driver
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger log = LoggerUtils.getLogger(DriverManager.class);

    private DriverManager() {} //私有构造，禁止外部实例化. 别创建我的对象，直接用我的静态方法就可以. 单例

    public static WebDriver getDriver(){
        //2.单例判断：当前线程未初始化driver时才创建
        if(driverThreadLocal.get() == null){
            String browserType = getBrowserTypeConfig();
            log.info("Initialize the browser:" + browserType);
            WebDriver driver = createDriver(browserType);
            driverThreadLocal.set(driver);
            log.info("Finish initial the chrome browser------");
        }
        return driverThreadLocal.get();
    }


    public static void quitDriver(){
        WebDriver driver = driverThreadLocal.get();
        if (driver !=null){
            try {
                driver.quit();
                log.info("Success to close the browser");
            }catch (Exception e){
                log.error("Failed to close the browser");
            }finally {
                driverThreadLocal.remove();
            }
        }
    }

    private static String getBrowserTypeConfig(){
        String browserType = ConfUtils.getProperty("browser.type");
        if (browserType == null || browserType.isEmpty()){
            throw new IllegalArgumentException("Do not config browser.type, please check the properties");
        }
        return browserType.toLowerCase();
    }

    private static WebDriver createDriver(String browserType) {
        WebDriver driver;
        switch (browserType) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "firefox":
                throw new UnsupportedOperationException("FireFox does not support");
            default:
                throw new IllegalArgumentException("The browser type does not support" + browserType + "Only support Chrome now");
        }
        return driver;
    }

    private static WebDriver createChromeDriver(){
        try{
            log.info("Start initial the chrome browser------");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            if("true".equals(ConfUtils.getProperty("chrome.headless"))){
                options.addArguments("--headless=new");
            }
            return new ChromeDriver(options);
        }catch (Exception e){
            log.error("Initialize chrome browser failure");
            throw new RuntimeException("Create ChromeDriver failure, please check the driver or browser version");
        }
    }
}
