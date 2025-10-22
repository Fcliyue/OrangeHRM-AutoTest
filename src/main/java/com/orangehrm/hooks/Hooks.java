package com.orangehrm.hooks;

import com.orangehrm.driver.DriverManager;
import com.orangehrm.utils.LoggerUtils;
import com.orangehrm.utils.ScreenShotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger log = LoggerUtils.getLogger();
    //private WebDriver driver;
    //Global hook:Runs before every scenario
    @Before
    public void setup(Scenario scenario){
        try{
            log.info("=======================================================");
            log.info("Starting scenario: "+ scenario.getName());
        }catch (Exception e){
            log.error("Failed to initialize driver for scenario: " + scenario.getName(),e);
            throw e;
        }

    }

    @After
    public void globalTeardown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                WebDriver driver = DriverManager.getDriver();
                String scenarioName = scenario.getName().replaceAll(" ", "_");
                String screenshotPath = ScreenShotUtils.captureScreenshot(driver, scenarioName);
                //scenario.attach("Screenshot saved to: " + screenshotPath, "text/plain", "Failure Screenshot Path");
                //byte[] screenshotBytes = ScreenShotUtils.captureScreenshot(driver,scenarioName);
                scenario.attach(screenshotPath,"image/png","Failure Screenshot");
            }
        } catch (Exception e) {
            log.error("CleanUp is error! "+e.getMessage());
        } finally {
            DriverManager.quitDriver();
            log.info("Browser closed successfully!");
        }
    }
}
