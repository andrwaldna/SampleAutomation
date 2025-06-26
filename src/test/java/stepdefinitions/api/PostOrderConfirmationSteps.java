package stepdefinitions.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pageobject.api.AddPet;
import pageobject.api.PostOrderConfirmation;

public class PostOrderConfirmationSteps {

    PostOrderConfirmation postOrderConfirmation = new PostOrderConfirmation();
    Response postOrderConfirmationResponse;

    @Given("I am an authorized user")
    public void i_am_an_authorized_user() {
        //For accessToken or authorization
        System.out.println("This is the authorization ");
    }

    @When("I send a POST request for order confirmation")
    public void i_send_a_post_request_for_order_confirmation(DataTable table) {
        try {
            postOrderConfirmationResponse = postOrderConfirmation.postOrderConfirmationResponse(table.asMaps());
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println(error.getMessage());
        }
    }

    @Then("I receive status code {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        postOrderConfirmation.validatePostOrderConfirmationStatusCode(postOrderConfirmationResponse, expectedStatusCode);
    }
}
