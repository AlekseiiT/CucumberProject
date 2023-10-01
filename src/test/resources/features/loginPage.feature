Feature: Login functionality for OrangeHRM website

  As a user of the OrangeHRM website
  I want to be able to log in with provided account
  SO that I can access my features and manage my main tasks

    Scenario:
      Given I have entered a valid username and password
      When I click on the login button
      Then I should be logged in successfully

