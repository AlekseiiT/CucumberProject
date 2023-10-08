package org.example.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.ApiManager;
import org.example.assertWrapper.SubunitResponseAssert;

public class SubunitStepDef {
    Response response;

    @When("I get available subunits")
    public void iGetAvailableSubunits() {
        response = ApiManager.getSubunit();
    }

    @Then("I check their values")
    public void iCheckTheirValues() {
        SubunitResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .metaOtherEmployeeCount(0)
                .metaSubunitCount(6)
                .metaUnassignedEmployeeCount(18)
                .dataCount(1)
                .subunitPresent("Engineering")
                .assertAll();
    }
}
