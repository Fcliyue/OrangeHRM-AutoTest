package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverManager;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginStepDefs {
    private final WebDriver driver = DriverManager.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("The OrangeHRM login page is launched")
    public void launchLoginPage() {
        loginPage.openLoginPage();
    }

    @When("Enter valid username and valid password")
    public void enterLoginCredentials() {
        String userName = ConfUtils.getProperty("valid.username");
        String passWord = ConfUtils.getProperty("valid.password");
        loginPage.enterLoginCredentials(userName, passWord);
    }

    @And("Click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("The login should be successful, and the Dashboard page is shown")
    public void verifySuccessfulLogin() {
        Assert.assertTrue(dashboardPage.isDashboardPageDisplay(), "Login success verification failed, the Dashboard page is not shown");
    }

    @And("Go to the PIM page")
    public void verifyGotoPIM() {
        dashboardPage.gotoPimPage();
        Assert.assertTrue(dashboardPage.isPimPageDisplay());
    }

    @Then("User logout")
    public void logout() {
        dashboardPage.logout();
    }

}
