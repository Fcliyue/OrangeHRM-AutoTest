package OneStep;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrangeLocatorTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\mytest\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                // 等待条件：元素可见（已渲染到页面，且宽高大于0）
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        try {
            WebElement usernameByName = driver.findElement(By.name("username"));
            WebElement usernameByXPTAH = driver.findElement(By.xpath("//input[@name='username']"));
            System.out.println("用户名输入框定位成功：" + usernameByXPTAH.isDisplayed());


            driver.findElement(By.name("password"));
            WebElement passwordByXPTH = driver.findElement(By.xpath("//input[@type='password']"));
            System.out.println("密码框定位成功" + passwordByXPTH.isDisplayed());

            driver.findElement(By.xpath("//button[@type='submit']"));
            WebElement loginButtonByCSS = driver.findElement(By.cssSelector("button[type='submit'"));
            System.out.println("登录按钮定位成功" + loginButtonByCSS);

            usernameByName.sendKeys("123443");
            passwordByXPTH.sendKeys("weweqeqw");
            loginButtonByCSS.click();
            Thread.sleep(3000);

            wait.until(
                    // 等待条件：元素可见（已渲染到页面，且宽高大于0）
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class, 'oxd-alert-content-text')]"))
            );
            WebElement errorMsgLocator = driver.findElement(By.xpath("//p[contains(@class, 'oxd-alert-content-text')]"));
            //WebElement invalidByClass = driver.findElement(By.className("oxd-text oxd-text--p oxd-alert-content-text"));
            System.out.println("xpath定位登录失败message成功" + errorMsgLocator);
            //System.out.println("class定位登录失败message成功" + invalidByClass);
        } catch (InterruptedException e) {
            e.getMessage();
        } finally {
            driver.quit();
        }
    }
}
