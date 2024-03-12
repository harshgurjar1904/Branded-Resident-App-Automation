Feature: Feature to test Login Functionality
  @LoginTest
  Scenario: Check login functionality with correct credential
    Given I am on login page
    When I am entering email address
    And I click on Login button
    And I am entering OTP
    Then I am on Home screen