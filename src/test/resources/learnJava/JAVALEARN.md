## 一、Java中常见的异常


### 1. Java 基础常见异常
| 异常类型                 | 含义    | 常见场景| 具体例子                                           |                      
|----------------------|-------|-----|------------------------------------------------|
| NullPointerException | 空指针异常 | 调用null对象的方法或属性| String s = null; s.length                      |
|ClassCastException|类型转换异常|强制转换不兼容的类型| Object obj = "hello";Integer i = (Integer) obj |
|IndexOutOfBoundsException|索引越界异常（ArrayIndexOutOfBoundsException、StringIndexOutOfBoundsException）|数组、字符串索引超出范围|int[] arr = new int[3]; arr[5]=1;|
|IllegalAgumentException|非法参数异常|方法接收无效参数|Collection.sort(null)|
|IllegalStateException|非法状态异常|对象状态不符合|如迭代器遍历集合时候，修改集合|
|ArithmeticException|算术异常|除以0|int a = 1/0 |
|NoSuchMethodException|方法不存在异常|反射调用不存在的方法|
|ClassNotFoundException|类未找到异常|反射接在类时类路径错误|Class.forName("com.Test") 但类不存在|
|UnsupportOperationException|不支持的操作异常|调用接口中未实现的方法|List.of(1,2).add(3) 不可变集合不支持add|

### 2. Java 项目开发中常见异常
| 异常类型        | 含义    | 常见场景| 具体例子                                    |                      
|-------------|-------|-----|-----------------------------------------|
| IOException |IO异常（checked 异常，必须处理）|文件读写错误| 如文件不存在，权限不足，流未关闭                        |
|FileNotFoundException|文件未找到异常（IOException的子类）|打开不存在的文件| new FileInputStream("a.txt")，但文件不存在     |
|SQLException|数据库操作异常|SQL 语法错误，连接失败，表不存在|
|TimeoutException|超市异常|网络请求超时，线程等待超时| Future.get(1,TimeUnit.SCONDS)超时         |
|InterruptedException|线程中断异常|线程休眠、等待时候被中断| Thread.sleep(10000)时候调用thread.interrupt |
|ConcurrentModificationException|并发修改异常|单线程中用迭代器遍历集合时候修改集合| for-each 循环中 list.remove                |
|JsonParseException|JSON解析异常，第三方开 如Jackson,Gson|JSON格式错误|如缺少引号，括号，不匹配|
|HttpMessageNotReadableException|HTTP消息不可读异常（SpringMvc）|前端传的JSON参数与后端实体类不匹配|


### 3. 自动化测试中常见异常
| 异常类型                   | 含义    | 常见场景| 具体例子                                           |                      
|------------------------|-------|-----|------------------------------------------------|
| NoSuchElementException |未找到元素异常|元素定位方式有误，元素未加载就操作|
|ElementNotVisibleException|元素不可见异常|元素存在单倍隐藏|display:none，无法点击或输入|
|ElementClickInterceptedException|元素点击被拦击异常|元素被其他浮层，如逛好，加载框遮挡，导致点击失败|
|StaleElementReferenceException|过时元素引用异常|元素被刷新后仍用旧引用操作|如页面跳转后，原页面元素失效|
|TimeoutException|超时异常|显示等待 WebDriverWait 超时|
|NoSuchWindowException|窗口不存在异常|切换敞口时候，目标窗口已经关闭或者句柄错误|
|NoSuchFrameException|框架不存在异常|切换iframe时候，iframe的定位表达式错误或者框架未加载|
|InvalidSelectorException|无效的选择器异常|元素定位表达式语法错误|
|SessionNotCreatedException|会话创建失败异常|浏览器驱动与浏览器不匹配|
|WebDriverException|WebDriver 基础异常|浏览器崩溃，驱动未启动等底层问题|


### 4. 面试中常考的异常相关问题

#### 1. NullPointerException 如何避免？
调用方法前判空（if (obj != null) { ... }）；
使用Optional类（Java 8+）；
避免返回null（返回空集合 / 空字符串而非null）。
#### 2. checked 异常和 unchecked 异常的区别？
checked：编译时必须处理（如IOException，需try-catch或throws）；
unchecked：运行时异常（如NullPointerException），编译时不强制处理。
#### 3. 自动化测试中如何处理 NoSuchElementException？
优化元素定位（用相对定位而非固定属性）；
增加显式等待（WebDriverWait）确保元素加载；
处理动态元素（如通过部分属性匹配contains）。
#### 4.项目中如何设计异常处理机制？
自定义业务异常（如UserNotFoundException），区分系统异常和业务异常；
全局异常处理器（如 Spring 的@ControllerAdvice）统一捕获和处理；
异常信息需包含上下文（如 “用户 ID=123 不存在”），方便调试。

在自动化测试项目中，异常处理机制的设计直接影响测试脚本的稳定性、可维护性和故障定位效率。一个好的异常处理机制能减少“假失败”（如偶发的元素加载慢），清晰暴露真实问题（如功能BUG），同时降低脚本维护成本。以下是实战中常用的设计思路，面试中可分层次阐述：


### 一、核心目标：让异常“可识别、可追溯、可恢复”
1. **可识别**：区分“偶发异常”（如网络波动）和“必然异常”（如功能BUG），避免一刀切。
2. **可追溯**：异常发生时记录足够上下文（如操作步骤、页面截图、元素信息），便于定位问题。
3. **可恢复**：对可重试的异常（如元素点击被遮挡）自动重试，减少人工干预。


### 二、具体设计方案（分层次实现）

#### 1. 基础层：统一封装“元素操作”，屏蔽重复异常处理
自动化测试中80%的异常来自元素交互（如`NoSuchElementException`、`ElementClickInterceptedException`），因此需将元素操作（点击、输入、获取文本等）封装成通用方法，在方法内部集中处理常见异常。

**示例（Java Selenium）**：
```java
public class ElementHandler {
    private WebDriver driver;
    private WebDriverWait wait;

    public ElementHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 封装“点击元素”方法，自带异常处理
    public void click(By locator) {
        try {
            // 先等待元素可点击
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (ElementClickInterceptedException e) {
            // 处理“被遮挡”：尝试滚动到元素再点击
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(500); // 等待滚动完成
            element.click();
        } catch (TimeoutException e) {
            // 超时异常：记录详细信息（定位符、当前URL、截图）
            String errorMsg = "元素点击超时，定位符：" + locator + "，URL：" + driver.getCurrentUrl();
            takeScreenshot(); // 自动截图
            throw new TestException(errorMsg, e); // 抛出自定义异常
        }
    }
}
```

**核心思路**：
- 用显式等待替代Thread.sleep()，减少因加载慢导致的偶发异常；
- 对常见异常（如被遮挡、超时）预设处理逻辑（重试、滚动、刷新）；
- 无法处理的异常，包装成自定义异常并附加上下文。


#### 2. 业务层：自定义异常体系，区分“测试框架异常”和“业务异常”
项目中需定义一套自定义异常，明确异常类型，避免被原生异常（如`WebDriverException`）淹没。

**常见自定义异常类型**：
- `TestInitException`：测试初始化失败（如驱动启动失败、环境配置错误）；
- `ElementOperateException`：元素操作异常（封装原生元素异常，附加业务场景）；
- `BusinessAssertException`：业务断言失败（如“预期金额为100，实际为90”，区别于框架异常）；
- `RetryFailedException`：多次重试后仍失败（用于标记“非偶发问题”）。

**示例**：
```java
// 自定义业务断言异常
public class BusinessAssertException extends RuntimeException {
    private String actual; // 实际值
    private String expected; // 预期值
    private String step; // 业务步骤

    public BusinessAssertException(String step, String expected, String actual) {
        super("业务断言失败：步骤[" + step + "]，预期[" + expected + "]，实际[" + actual + "]");
        this.step = step;
        this.expected = expected;
        this.actual = actual;
    }
}

// 使用场景：验证员工姓名
public void verifyEmployeeName(String expectedName) {
    String actualName = elementHandler.getText(By.id("employeeName"));
    if (!actualName.equals(expectedName)) {
        throw new BusinessAssertException("验证员工姓名", expectedName, actualName);
    }
}
```

**核心思路**：
- 自定义异常携带业务上下文（如步骤、预期/实际值），方便定位是“脚本问题”还是“功能BUG”；
- 框架异常（如驱动崩溃）和业务异常（如数据错误）分开处理，避免混淆。


#### 3. 执行层：全局异常捕获+重试机制，提升脚本稳定性
在测试用例执行入口（如TestNG的`@BeforeMethod`/`@AfterMethod`、Cucumber的Hook）设置全局异常处理器，统一处理未捕获的异常，并对可重试场景自动重试。

**示例（TestNG全局处理）**：
```java
public class TestListener implements ITestListener {
    // 用例失败时触发
    @Override
    public void onTestFailure(ITestResult result) {
        Throwable exception = result.getThrowable();
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        // 1. 记录异常详情（日志+截图）
        log.error("用例失败：" + result.getName(), exception);
        if (driver != null) {
            takeScreenshot(driver, result.getName()); // 保存截图到报告目录
        }

        // 2. 对可重试异常（如偶发的点击失败）进行重试
        if (exception instanceof ElementOperateException && result.getRetryCount() < 2) {
            result.setStatus(ITestResult.SKIP); // 标记为跳过，触发重试
            log.info("触发重试，用例：" + result.getName() + "，重试次数：" + (result.getRetryCount() + 1));
        }
    }
}
```

**核心思路**：
- 用例失败时自动截图、记录日志（包含时间、用例名、异常栈），为后续排查提供依据；
- 对“偶发异常”（如网络波动导致的超时）设置有限重试（通常1-2次），减少假失败；
- 重试次数和重试条件可配置（如只重试`ElementClickInterceptedException`，不重试`BusinessAssertException`）。


#### 4. 报告层：异常可视化，区分“脚本错误”和“产品BUG”
在测试报告（如Allure、ExtentReports）中，根据异常类型分类展示：
- 若为`BusinessAssertException`：标记为“产品BUG”，显示业务步骤、预期/实际值；
- 若为`ElementOperateException`：标记为“脚本问题”，显示元素定位符、截图；
- 若为`TestInitException`：标记为“环境问题”，显示环境配置详情。

**示例（Allure报告标注）**：
```java
// 在异常处理时添加Allure标签
if (exception instanceof BusinessAssertException) {
    Allure.label("issueType", "产品BUG");
    Allure.attachment("实际结果", ((BusinessAssertException) exception).getActual());
} else if (exception instanceof ElementOperateException) {
    Allure.label("issueType", "脚本问题");
    Allure.attachment("元素定位符", ((ElementOperateException) exception).getLocator());
}
```


### 三、总结：设计原则
1. **分层处理**：基础操作层（元素交互）、业务逻辑层（自定义异常）、执行控制层（全局捕获+重试）、报告层（可视化）各司其职；
2. **减少“一刀切”**：区分可恢复异常（重试）和不可恢复异常（终止并记录）；
3. **上下文优先**：任何异常都要携带“何时、何地、做了什么”的信息（时间、URL、步骤、截图）；
4. **易于维护**：异常处理逻辑集中管理（如封装在工具类），避免每个用例重复写`try-catch`。

这套机制能有效提升自动化脚本的“抗干扰能力”，让测试结果更可信，同时降低问题排查成本，是面试中体现工程实践能力的重要亮点。










## 无序列表
- 项目1
- 项目2
    - 子项目
    - 子项目

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Markdown!");
    }
}
```
