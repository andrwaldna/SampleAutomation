package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobject.AutomationPractice;

public class AutomationPracticeSteps {
    WebDriver driver;
    AutomationPractice automationPractice = new AutomationPractice();;

    @Given("User is on the Testing Homepage")
    public void userIsOnTheTestingHomepage() {
        driver = automationPractice.openAutomationExerciseHomepage();
    }

    @When("User clicks on Signup link")
    public void userClicksOnSignupOrLoginButton() {
        automationPractice.clickSignUpOrLoginLink();
    }

    @When("User is redirected to the {string} page")
    public void userIsRedirectedToThePage(String signUpLabel) {
        assert automationPractice.redirectsToNewUserSignupPage().toLowerCase().contains(signUpLabel.toLowerCase());
        System.out.println("The label on the page for Sign up is " + automationPractice.redirectsToNewUserSignupPage().toLowerCase());
        System.out.println("The needed label on the page for Sign up is " + signUpLabel.toLowerCase());
    }

    @When("User inputs valid Name and Email Address")
    public void userInputValidNameAndEmailAddress() {
        automationPractice.inputValidNameAndEmailAddress();
    }

    @When("User clicks the Signup button")
    public void userClicksTheSignUpButton() {
        automationPractice.clickSignUpButton();
    }

    @Then("User should be redirected to {string} Page")
    public void redirectsToEnterAccountInformationPage(String enterAccountInformationLabel) {
        assert automationPractice.redirectsToEnterAccountInformationPage().toLowerCase().contains(enterAccountInformationLabel.toLowerCase());
        System.out.println("The label on the page is " + automationPractice.redirectsToEnterAccountInformationPage().toLowerCase());
        System.out.println("The needed label on the page is " + enterAccountInformationLabel.toLowerCase());
        automationPractice.closeBrowser();
    }

}
