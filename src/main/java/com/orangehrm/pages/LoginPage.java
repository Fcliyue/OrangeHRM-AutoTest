package com.orangehrm.pages;

import com.orangehrm.utils.ConfUtils;
import com.orangehrm.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final String USERNAME_INPUT_BY_NAME = "username";
    private final String PASSWORD_INPUT_BY_NAME = "password";
    private final String LOGIN_BUTTON_BY_XPATH = "//button[@type='submit']";
    private final String ERROR_MESSAGE_BY_XPATH = "//span[contains(@class, 'oxd-input-field-error-message')] | //p[contains(@class, 'oxd-alert-content-text')]";
    private final String PAGE_TITLE_BY_XPATH = "//title[text()='OrangeHRM']";
    private final String PAGE_URL = "https://opensource-demo.orangehrmlive.com";
    private final String DASHBOARD_URL = "/dashboard";
    public static final String LOGIN_PAGE_URL = "https://opensource-demo.orangehrmlive.com/";  //改成静态变量，供外部使用

    public LoginPage (WebDriver driver){
        super(driver);
    }
    public void openLoginPage() {
        openUrl(PAGE_URL);
    }
    public WebElement getUsernameInput() {
        return seleniumUtils.findElement(driver, "name", USERNAME_INPUT_BY_NAME);
    }

    public WebElement getPasswordInput() {
        return seleniumUtils.findElement(driver, "name", PASSWORD_INPUT_BY_NAME);
    }

    public WebElement getLoginButton() {
        return seleniumUtils.findElement(driver, "xpath", LOGIN_BUTTON_BY_XPATH);
    }

    public String getTitle(){
        return seleniumUtils.getElementTitle(driver);
    }

    public String gerErrorMessage() {
        WebElement element = seleniumUtils.findElement(driver, "xpath", ERROR_MESSAGE_BY_XPATH);
        return element.getText();
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    public void enterLoginCredentials(String username, String password) {
        seleniumUtils.sendKeys(getUsernameInput(), username);
        seleniumUtils.sendKeys(getPasswordInput(), password);
        System.out.println("Enter username: " + username + "; Enter password: " + password);
    }

    public DashboardPage goToDashboardPage(){
        waitForPageLoad(DASHBOARD_URL);
        return new DashboardPage(driver);
    }

    public boolean isLoginPageDisplayed() {
        return seleniumUtils.isPageDisplayed(driver, "xpath",PAGE_TITLE_BY_XPATH);
    }

}
