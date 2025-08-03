@Smoke
Feature: Login functionality

  @smoke @login
  @DataFile_1001.json
  Scenario: Valid user login
    Given user is on login page
    Then verify user is logged in successfully

