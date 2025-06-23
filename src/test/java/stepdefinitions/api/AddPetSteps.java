package stepdefinitions.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pageobject.api.AddPet;

import static org.junit.Assert.assertEquals;


public class AddPetSteps {

    AddPet addPet = new AddPet();
    Response postAddPetResponse;

    @Given("I am an authorized User")
    public void i_am_an_authorized_user() {
        //For accessToken or authorization
        System.out.println("This is the authorization ");
    }

    @When("I send a POST request to add the pet with details")
    public void i_send_a_post_request_to_add_pet(DataTable table) {
        try {
            postAddPetResponse = addPet.postAddPetResponse(table.asMaps());
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println(error.getMessage());
        }
    }

    @Then("I receive status code {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        addPet.validatePostAddPetStatusCode(postAddPetResponse, expectedStatusCode);
    }
}


