package pageobject;

import instance.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutomationPractice extends PageBase {
    By bySignUpLink = By.xpath("//*[contains(text(),' Signup / Login')]");
    By byNewUserSignUpLabel = By.xpath("//h2[contains(text(), 'New User Signup!')]");
    By byName = By.name("name");
    By byEmail = By.xpath("//input[@data-qa=\"signup-email\"]");
    By bySignUpButton = By.xpath("//button[@data-qa=\"signup-button\"]");
    By byEnterAccountInformationLabel = By.xpath("//*[contains(text(), 'Enter Account Information')]");

    public WebDriver openAutomationExerciseHomepage() {
        initializeDriver("https://www.automationexercise.com/");
        return driver;
    }

    public void clickSignUpOrLoginLink() {
        WebElement signUpLink = driver.findElement(bySignUpLink);
        click(signUpLink);
    }

    public String redirectsToNewUserSignupPage() {
        WebElement newUserSignUpLabel = driver.findElement(byNewUserSignUpLabel);
        return newUserSignUpLabel.getText();
    }

    public void inputValidNameAndEmailAddress() {
        WebElement name = driver.findElement(byName);
        WebElement email = driver.findElement(byEmail);
        sendKeys(name, "For testing only name");
        sendKeys(email, "fortestingonly@test.com");
    }

    public void clickSignUpButton(){
        WebElement signUpButton = driver.findElement(bySignUpButton);
        click(signUpButton);
    }

    public String redirectsToEnterAccountInformationPage() {
        WebElement enterAccountInformationLabel = driver.findElement(byEnterAccountInformationLabel);
        return enterAccountInformationLabel.getText();
    }
    public void closeBrowser() {
        quitDriver();
    }
}
