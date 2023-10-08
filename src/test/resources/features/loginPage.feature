Feature: Login functionality for OrangeHRM website

  As a user of the OrangeHRM website
  I want to be able to log in with provided account
  SO that I can access my features and manage my main tasks

  @UiScenario
  Scenario: Get api token
    Given I have entered a valid username and password
    When I click on the login button
    Then I get api token

  @UiScenario
  Scenario: Valid login
    Given I have entered a valid username and password
    When I click on the login button
    Then I should be logged in successfully

  @UiScenario
  Scenario Outline: Invalid login with parameters
    Given I have entered invalid "<username>" and "<password>"
    When I click on the login button
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username             | password    | error_message       |
      | invaliduser@mail.com | invalidPass | Invalid credentials |
      | adminInvalid         | validPass   | Invalid credentials |
      | validMail@mail.com   | invalidPass | Invalid credentials |

  @UiScenario
  Scenario: Navigating to the forgotten password page
    When I click on the Forgot your password? link
    Then I should be redirected to the password reset page