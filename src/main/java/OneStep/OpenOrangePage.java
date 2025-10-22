package OneStep;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenOrangePage {
    public static void main(String[] args) {
        //1. 配置 ChromeDriver 路径（指向项目下的 drivers 文件夹）
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\mytest\\chromedriver-win64\\chromedriver.exe");
        //2. 初始化 WebDriver（打开 Chrome 浏览器）
        WebDriver driver = new ChromeDriver();
        //最大化浏览器窗口（避免元素被遮挡）
        driver.manage().window().maximize();
        //3. 访问 OrangeHRM 演示站登录页（固定地址）
        String URL = "https://opensource-demo.orangehrmlive.com/";
        driver.get(URL);
        System.out.println("页面访问成功");
        //4. 验证页面标题（OrangeHRM 登录页标题固定为 "OrangeHRM"）
        String ActualTitle = driver.getTitle();
        String ExpectTitle = "OrangeHRM";
        if (ExpectTitle.equals(ActualTitle)) {
            System.out.println("页面标题显示正确！");
        } else {
            System.out.println("页面标题不正确");
        }
        //5. 关闭浏览器（避免残留进程）
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
