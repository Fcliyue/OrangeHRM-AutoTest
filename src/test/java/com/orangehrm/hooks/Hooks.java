package com.orangehrm.hooks;

import com.orangehrm.driver.DriverManager;
import com.orangehrm.utils.LoggerUtils;
import com.orangehrm.utils.ScreenShotUtils;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static final Logger log = LoggerUtils.getLogger();
    private static WebDriver driver;

    //private WebDriver driver;
    //Global hook:Runs before every scenario
    @BeforeAll
    public static void setup() {
        log.info("===============Global Setup=====================");
    }

    @Before
    public void scenarioSetup(Scenario scenario) {
        try {
            log.info("=======================================================");
            log.info("Starting scenario: " + scenario.getName());
            driver = DriverManager.getDriver();
        } catch (Exception e) {
            log.error("Failed to initialize driver for scenario: " + scenario.getName(), e);
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
                scenario.attach(screenshotPath, "image/png", "Failure Screenshot");
            }
        } catch (Exception e) {
            log.error("CleanUp is error! " + e.getMessage());
        } finally {
            DriverManager.quitDriver();
            log.info("Browser closed successfully!");
        }
    }

    @AfterAll
    public static void clear() {
        log.info("===============Global Clear=====================");
    }
}
