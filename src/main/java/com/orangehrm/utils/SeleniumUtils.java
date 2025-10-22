package com.orangehrm.utils;

import com.orangehrm.utils.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
    public SeleniumUtils() {
    }
    private static final int WAIT_TIMEOUT = 20;

    public WebElement findElement(WebDriver driver,String locatorType,String locatorValue){
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorUtils.locator(locatorType,locatorValue)));
    }

    public void waitElementClickable(WebDriver driver,WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


    //等待元素可见后，输入文本
    public void sendKeys(WebDriver driver, String locatorType, String locatorValue, String keys) {
        WebElement element = findElement(driver,locatorType,locatorValue);
        element.clear();
        element.sendKeys(keys);
        System.out.println("向元素：[ " + locatorValue + " ] 输入文本：" + keys);
    }

    public void sendKeys(WebElement element,String keys) {
        element.sendKeys(keys);
        System.out.println("向元素：[ " + element + " ] 输入文本：" + keys);
    }
    public void click(WebDriver driver, String locatorType, String locatorValue) {
        WebElement element = findElement(driver,locatorType,locatorValue);
        element.click();
    }

    public String getElementText(WebDriver driver, String locatorType, String locatorValue) {
        WebElement element = findElement(driver,locatorType,locatorValue);
        return element.getText();
    }

    public String getElementTitle(WebDriver driver){
        return driver.getTitle();
    }
    public Boolean isPageDisplayed(WebDriver driver,String locatorType, String locatorValue){
        try {
            WebElement element = findElement(driver,locatorType,locatorValue);
            return element.isDisplayed();
        }catch (Exception e){
            System.out.println("Page is not displayed" + e);
            return false;
        }
    }
}
