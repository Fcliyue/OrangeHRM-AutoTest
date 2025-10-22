package com.orangehrm.pages;

import com.orangehrm.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {


    private final String PAGE_URL = "https://opensource-demo.orangehrmlive.com";
    private final String DASHBOARD_URL = "/dashboard";
    public static final String LOGIN_PAGE_URL = "https://opensource-demo.orangehrmlive.com/";//改成静态变量，供外部使用

    SeleniumUtils seleniumUtils = new SeleniumUtils();
    @FindBy(name = "username")
    private WebElement usernameInput;
    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[contains(@class, 'oxd-input-field-error-message')] | //p[contains(@class, 'oxd-alert-content-text')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//title[text()='OrangeHRM']")
    private WebElement pageTitle;

    public LoginPage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public void openLoginPage() {
        openUrl(PAGE_URL);
    }
    public void enterUserName(String username) {
        seleniumUtils.waitElementClickable(driver,usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        seleniumUtils.waitElementClickable(driver,passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public String getTitle(){
        return seleniumUtils.getElementTitle(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void enterLoginCredentials(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        System.out.println("Enter username: " + username + "; Enter password: " + password);
    }

    public DashboardPage goToDashboardPage(){
        waitForPageLoad(DASHBOARD_URL);
        return new DashboardPage(driver);
    }


}
