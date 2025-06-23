package pageobject.web;

import instance.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage extends PageBase {
    By searchBox = By.name("q");
    By searchButton = By.name("btnK");
    By firstResult = By.cssSelector("h3");

    public WebDriver openGoogleHomePage() {
        initializeDriver("https://www.google.com/");
        return driver;
    }

    public void enterSearchTerm(String term) {
        WebElement searchInput = driver.findElement(searchBox);
        sendKeys(searchInput, term);
    }

    public void submitSearch() {
        WebElement searchBtn = driver.findElement(searchButton);
        searchBtn.submit();
    }

    public String getFirstResultText() {
        WebElement firstResultElement = driver.findElement(firstResult);
        return firstResultElement.getText();
    }

    public void closeBrowser() {
        quitDriver();
    }
}
