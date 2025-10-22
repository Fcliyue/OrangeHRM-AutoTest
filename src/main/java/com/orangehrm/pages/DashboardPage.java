package com.orangehrm.pages;

import com.orangehrm.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

import static com.orangehrm.pages.LoginPage.LOGIN_PAGE_URL;

public class DashboardPage extends BasePage {
    private final String DASHBOARD_BY_XPATH = "//h6[text()='Dashboard']";
    private final String USERNAME_BY_CLASS = "oxd-userdropdown-name";
    private final String USERNAME_DROPDOWN_BY_CLASS = "oxd-userdropdown";
    private final String LOGOUT_BY_XPATH = "//a[contains(@class,'oxd-userdropdown-link') and text()='Logout']";
    private final String ADMIN_LINK_BY_XPATH = "//a[contains(@href,'/web/index.php/admin/viewAdminModule') and text()='Admin']";
    private final String PIM_LINK_BY_XPATH = "//a[contains(@href,'/web/index.php/pim/viewPimModule')]";
    private final String MAIN_MANU_BUTTON_BY_XPATH = "//button[contains(@class,'oxd-main-menu-button')]";
    private final String PIM_PAGE_HEADER_BY_XPATH = "//h6[text()='PIM']";

    SeleniumUtils seleniumUtils = new SeleniumUtils();

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardPageDisplay() {
        WebElement header = seleniumUtils.findElement(driver,"xpath",DASHBOARD_BY_XPATH);
        WebElement welcomeUser = seleniumUtils.findElement(driver,"class",USERNAME_BY_CLASS);
        return header.isDisplayed()&&welcomeUser.isDisplayed();
    }

    public String getLoggedInUsername() {
        WebElement welcomeUser = seleniumUtils.findElement(driver, "class", USERNAME_BY_CLASS);
        return welcomeUser.getText().trim();
    }

    public LoginPage logout(){
        seleniumUtils.click(driver,"class",USERNAME_DROPDOWN_BY_CLASS);
        seleniumUtils.click(driver,"xpath",LOGOUT_BY_XPATH);
        waitForPageLoad(LOGIN_PAGE_URL);
        return new LoginPage(driver);
    }

    public void gotoPimPage(){
        seleniumUtils.click(driver,"xpath",PIM_LINK_BY_XPATH);
        waitForPageLoad("/pim");
    }

    public boolean isPimPageDisplay(){
        WebElement header = seleniumUtils.findElement(driver,"xpath",PIM_PAGE_HEADER_BY_XPATH);
        return header.isDisplayed();
    }
}
