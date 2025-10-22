package OneStep;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExceptionTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\tools\\mytest\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        try {
            WebElement element = findElement(driver, By.id("invalidId"));
        } catch (NoSuchElementException e) {
            System.out.println("测试执行失败" + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("元素定位异常" + locator, e);
        }
    }
}
