package instance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static void initializeDriver(String url) {
        System.setProperty("webdriver.chrome.driver", "src/main/java/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get(url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // Wait for an element to be visible
    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for an element to be clickable
    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Click an element
    protected void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    // Get text from an element
    protected String getText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }

    // Check if an element is displayed
    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Check if an element is enabled
    protected boolean isElementEnabled(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
