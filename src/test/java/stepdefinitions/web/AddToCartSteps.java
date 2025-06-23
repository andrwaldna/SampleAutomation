package stepdefinitions.web;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageobject.web.AddToCart;

public class AddToCartSteps {
    AddToCart addToCart = new AddToCart();

    @Given("User is on Sauce Demo login page")
    public void userIsOnSauceDemoLoginPage() {
        addToCart.openUrl("https://www.saucedemo.com/v1/");
    }

    @When("User inputs {string} in username and {string} in password")
    public void userSearchesForString(String username, String password) {
        addToCart.enterUsername(username,password);
        addToCart.clickLogin();
    }

//    @Then("search results for {string} should be displayed")
//    public void searchResultsForStringShouldBeDisplayed(String searchTerm) {
//        assert addToCart.getFirstResultText().toLowerCase().contains(searchTerm.toLowerCase());
//        System.out.println("THIS IS THE SEARCH TERM THAT IS RETRIEVED FROM LOCATOR " + addToCart.getFirstResultText().toLowerCase());
//        System.out.println("THIS IS THE SEARCH NEEDED " + searchTerm);
//    }
}
