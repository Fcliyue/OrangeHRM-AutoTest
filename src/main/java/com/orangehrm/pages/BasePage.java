package com.orangehrm.pages;

import com.orangehrm.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base page class for all page Objects
 * Encapsulate common methods used across all pages
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SeleniumUtils seleniumUtils;
    private static final int DEFAULT_WAIT_TIMEOUT = 15;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);
        this.seleniumUtils = new SeleniumUtils();
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
