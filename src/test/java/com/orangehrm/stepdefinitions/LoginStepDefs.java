package com.orangehrm.stepdefinitions;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfUtils;
import com.orangehrm.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import com.orangehrm.utils.ScreenShotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginStepDefs {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private static final Logger log = LoggerUtils.getLogger();

  @Before
    public void setupBrowser() {
        log.debug("Initializing WebDriver");
        System.out.println("Start----------------");
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\mytest\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @After
    public void closeBrowser(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                String scenarioName = scenario.getName().replaceAll(" ", "_");
                String screenshotPath = ScreenShotUtils.captureScreenshot(driver, scenarioName);
                scenario.attach("Screenshot saved to: " + screenshotPath, "text/plain", "Failure Screenshot Path");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully");
            }
        }
    }
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
    public void logout(){
        dashboardPage.logout();
    }

/*    @Then("The login should fail, and error message {string} should be displayed")
    public void verifyFailedLogin(String expectedText) {
        String text = loginPage.gerErrorMessage();
        Assert.assertEquals(text, expectedText, "'Login failure verification failed: Error message mismatch");
    }*/

}
