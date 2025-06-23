package stepdefinitions.web;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageobject.web.GoogleHomePage;

public class GoogleSearchSteps {
    WebDriver driver;
    GoogleHomePage googleHomePage;

    @Given("User is on the Google homepage")
    public void userIsOnTheGoogleHomepage() {
        googleHomePage = new GoogleHomePage();
        driver = googleHomePage.openGoogleHomePage();
    }

    @When("User searches for {string}")
    public void userSearchesForString(String searchTerm) {
        googleHomePage.enterSearchTerm(searchTerm);
        googleHomePage.submitSearch();
    }

    @Then("search results for {string} should be displayed")
    public void searchResultsForStringShouldBeDisplayed(String searchTerm) {
        assert googleHomePage.getFirstResultText().toLowerCase().contains(searchTerm.toLowerCase());
        System.out.println("THIS IS THE SEARCH TERM THAT IS RETRIEVED FROM LOCATOR " + googleHomePage.getFirstResultText().toLowerCase());
        System.out.println("THIS IS THE SEARCH NEEDED " + searchTerm);
        googleHomePage.closeBrowser();
    }
}
