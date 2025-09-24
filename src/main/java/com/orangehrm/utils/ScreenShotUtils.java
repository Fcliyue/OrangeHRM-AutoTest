package com.orangehrm.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotUtils {
    private static final String SCREENSHOT_DIR = "target/cucumber-screenshots/";

    static {
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static String captureScreenshot(WebDriver driver, String scenarioName) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotFileName = scenarioName + "_" + timeStamp + ".png";
        Path screenshotPath = Paths.get(SCREENSHOT_DIR + screenshotFileName);
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshotsBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            Files.write(screenshotPath, screenshotsBytes);
            LoggerUtils.getLogger().info("Screenshot saved to: " + screenshotPath.toAbsolutePath());
        } catch (IOException e) {
            LoggerUtils.getLogger().error("Failed to capture screenshot" + e.getMessage());
        }
        return screenshotPath.toString();
    }
}
