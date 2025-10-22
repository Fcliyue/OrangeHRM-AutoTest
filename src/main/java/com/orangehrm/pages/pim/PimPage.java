package com.orangehrm.pages.pim;

import com.orangehrm.pages.BasePage;
import com.orangehrm.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class PimPage extends BasePage {
    private static final Logger log = LoggerUtils.getLogger();
    private final String ADD_EMPLOYEE_PAGE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee";
    private final String PERSON_DETAIL_PAGE_URL = "/pim/viewPersonalDetails/empNumber";
    private final String imageRelativePath = "src/test/resources/testPhotos/employeeImage.png";
    @FindBy(xpath = "//button[text()=' Add ']")
    private WebElement addEmployeeButton;

    @FindBy(name = "firstName")
    private WebElement firstNameInput;
    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[contains(text(),'Employee Id')]/ancestor::div[contains(@class, 'oxd-input-group')]//input")
    private WebElement employeeIdInput;

    @FindBy(xpath = "//input[@class='oxd-file-input']")
    private WebElement employeeImageUpload;
    private final String AVATAR_UPLOAD_BY_XPATH = "//input[@class='oxd-file-input']";

    @FindBy(xpath = "//button[text()=' Save ']")
    private WebElement saveButton;

    public PimPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    public void AddEmployeePage() {
        openUrl(ADD_EMPLOYEE_PAGE_URL);
    }

    public void enterFirstName(String firstName) {
        log.info("Entering first name: " + firstName);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        log.info("Entering last name: " + lastName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmployeeId(String employeeId) {
        log.info("Enter employeeId: " + employeeId);
        employeeIdInput.clear();
        employeeIdInput.sendKeys(employeeId);
    }

    public void uploadAvatar() {
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AVATAR_UPLOAD_BY_XPATH)));
        //若input被隐藏，用JS使其可见
        if (!fileInput.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", fileInput);
        }
        //转换为绝对路径，解决相对路径在不同环境下的解析问题
        File imageFile = new File(imageRelativePath);
        String absolutePath = imageFile.getAbsolutePath();

        fileInput.sendKeys(absolutePath);

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(
                By.cssSelector("img.employee-image"), "src", "default-photo.png")));
    }

    public void saveEmployeeInfo(){
        log.info("Click save button.");
        saveButton.click();
    }

    public String getEmployeeFirstName(){
        return firstNameInput.getText();
    }
    public void goToPersonDetails(){
        waitForPageLoad(PERSON_DETAIL_PAGE_URL);
    }
}
