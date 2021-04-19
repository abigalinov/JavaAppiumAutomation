import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackages", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/andykravz/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {

        // вызов метода waitForElementAndClick()
        waitForElementByXpathAndClick(
                "//*[contains(@text, 'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        // вызов метода waitForElementAndSendKeys()
        waitForElementByXpathAndSendKeys(
                "//*[contains(@text, 'Search…')]",
                "Java",
                "Cannot find Search… input",
                5
        );

        // вызов метода waitForElementPresentByXpath()
        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementByIdAndClick(
                "org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementByIdAndClick(
                "org.wikipedia:id/search_close_btn",
                "Cannot find X cancel search",
                5
        );

        waitForElementNotPresent(
                "org.wikipedia:id/search_close_btn",
                "X is still present on the page",
                5
        );
    }

    // == ОПРЕДЕЛЕНИЕ МЕТОДОВ ==
    // не входит в тест

    // методы поиска по xpath, ошибки и ожидания с перегрузкой
    private WebElement waitForElementPresentByXpath(String xpath, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String errorMessage) {
        return waitForElementPresentByXpath(xpath, errorMessage, 5);
    }

    // метод, в котором дожидаемся элемента по xpath, затем кликаем по нему
    private WebElement waitForElementByXpathAndClick(String xpath, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresentByXpath(xpath, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    // метод в котором дожидаемся элементы по xpath и отправляем значение
    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresentByXpath(xpath, errorMessage, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    // метод поиска по ID
    private WebElement waitForElementPresentById(String id, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    // метод клика по id
    private WebElement waitForElementByIdAndClick(String id, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresentById(id, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    // метод ожидающий, что элемента на экране нет
    private boolean waitForElementNotPresent(String id, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

}
