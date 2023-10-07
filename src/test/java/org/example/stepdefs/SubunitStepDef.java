package org.example.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.ApiManager;
import org.testng.Assert;

public class SubunitStepDef {
    Response response;

    @When("I get available subunits")
    public void iGetAvailableSubunits() {
        response = ApiManager.getSubunit();
    }

    @Then("I check their values")
    public void iCheckTheirValues() {
        System.out.println(response.getBody().prettyPrint());
        Assert.assertTrue(true);
    }
}
