package pageobject.web;

import instance.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddToCart extends PageBase {
    By userNameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    By searchButton = By.name("btnK");
    By firstResult = By.cssSelector("h3");

    public WebDriver openUrl(String url) {
        initializeDriver(url);
        return driver;
    }

    public void enterUsername(String username, String password) {
        WebElement usernameElement = driver.findElement(userNameField);
        sendKeys(usernameElement, username);
        WebElement passwordElement = driver.findElement(passwordField);
        sendKeys(passwordElement, password);
    }

    public void clickLogin() {
        WebElement loginElement = driver.findElement(loginButton);
        loginElement.click();
    }

//    public String getFirstResultText() {
//        WebElement firstResultElement = driver.findElement(firstResult);
//        return firstResultElement.getText();
//    }
}
