Feature: Subunit check

  As a user of the OrangeHRM website
  I want to be able to check available subunits

  Scenario: Check available subunits
    When I get available subunits
    Then I check their values
