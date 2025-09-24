package com.orangehrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocatorUtils {
    public static By locator(String locatorType, String locatorValue){
        switch (locatorType.toLowerCase()){
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "cssSelector":
                return By.cssSelector(locatorValue);
            default:
                throw new IllegalArgumentException("不支持的类型"+locatorType+" 仅支持 id,name,xpath,css");
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\tools\\mytest\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebDriverWait wait = new WebDriverWait(driver,10);
        try{        wait.until(ExpectedConditions.visibilityOfElementLocated(locator("name","username")));
            System.out.println("name定位成功");
        }catch(NoSuchElementException e){
            e.getMessage();
        }finally {
            driver.quit();
        }

    }
}
