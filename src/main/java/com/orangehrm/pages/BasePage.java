package com.orangehrm.pages;


import com.orangehrm.driver.DriverManager;
import com.orangehrm.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Base page class for all page Objects
 * Encapsulate common methods used across all pages
 */
public class BasePage {
    protected final WebDriver driver = DriverManager.getDriver();
    protected WebDriverWait wait;
    protected SeleniumUtils seleniumUtils;
    private static final int DEFAULT_WAIT_TIMEOUT = 15;

    public BasePage(WebDriver driver) {
        this.wait = new WebDriverWait(this.driver, DEFAULT_WAIT_TIMEOUT);
        PageFactory.initElements(this.driver,this);
    }

    public void openUrl(String url) {
        driver.get(url);
        System.out.println("Open url: " + url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle() {
        return driver.getTitle();
    }

    public void waitForPageLoad(String urlKey) {
        wait.until(driver -> driver.getCurrentUrl().contains(urlKey));
    }
}
