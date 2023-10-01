package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.example.driver.Driver;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.ResetPasswordPage;
import org.testng.Assert;

public class LoginPageStepDef {
    private LoginPage loginPage;

    @Before
    public void setup() {
        Driver.initDriver();
    }

    @After
    public void tearDown() {
        Driver.quitDriver();
    }

    @Given("I have entered a valid username and password")
    public void i_have_entered_a_valid_username_and_password() {
        new LoginPage()
                .enterLogin("Admin")
                .enterPassword("admin123");
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        new LoginPage().clickLoginBtn();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertTrue(new MainPage().isProfileImagePresent());
    }

    @Given("I have entered invalid {string} and {string}")
    public void iHaveEnteredInvalidAnd(String username, String password) {
        new LoginPage()
                .enterLogin(username)
                .enterPassword(password);
    }

    @Then("I should see an error message indicating {string}")
    public void iShouldSeeAnErrorMessageIndicating(String errorMessage) {
        Assert.assertTrue(new LoginPage().isErrorPresent(errorMessage));
    }

    @SneakyThrows
    @When("I click on the Forgot your password? link")
    public void iClickOnTheForgotYourPasswordLink() {
        new LoginPage().clickForgetPassLink();
        Thread.sleep(1000);
    }

    @SneakyThrows
    @Then("I should be redirected to the password reset page")
    public void iShouldBeRedirectedToThePasswordResetPage() {
        Assert.assertTrue(new ResetPasswordPage().getResetPasswordUrl().contains("auth/requestPasswordResetCode"));
        Thread.sleep(1000);
    }
}
