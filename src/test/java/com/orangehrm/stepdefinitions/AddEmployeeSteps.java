package com.orangehrm.stepdefinitions;


import com.orangehrm.driver.DriverManager;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.pim.PimPage;
import com.orangehrm.utils.LoggerUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class AddEmployeeSteps {
    private final WebDriver driver = DriverManager.getDriver();
    private final PimPage pimPage = new PimPage(driver);
    private static final Logger log = LoggerUtils.getLogger();

    @When("Add Employee page is displayed")
    public void addEmployeePageIsShown(){
        pimPage.clickAddEmployeeButton();
        pimPage.isAddEmployeePageDisplayed();
    }
    @And("I enter first name {string} last name {string}")
    public void enterEmployeeName(String firstName, String lastName){
        pimPage.enterFirstName(firstName);
        pimPage.enterLastName(lastName);
    }
    @And("I enter employeeId {string}")
    public void enterEmployeeId(String employeeId){
        pimPage.enterEmployeeId(employeeId);
    }

    @And("I upload employee avatar")
    public void uploadEmployeeAvatar(){
        pimPage.uploadAvatar();
    }

    @And("I click the save button")
    public void saveEmployeeInfo(){
        pimPage.saveEmployeeInfo();
    }

    @Then("I should be redirected to PersonalDetails page")
    public void personalDetailsPage(){
        pimPage.goToPersonDetails();
        log.info("Redirect to Personal details page!");
    }

    @And("the employee details page should display first name {string}")
    public void displayEmployeeInfo(String firstName){
        Assert.assertEquals(pimPage.getEmployeeFirstName(),firstName);
    }
}
