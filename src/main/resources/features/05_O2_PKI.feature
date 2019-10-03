@pki
Feature: O2_PKI

    Scenario: Start Selenium
      Given I am starting the selenium server

    Scenario: Start browser
      Given I am starting the browser

    Scenario: Missing PKI
      Given that I try to enter the O2 pki home page without a pki
      Then it does not let me into O2

    Scenario: Stop browser
      Given I am stopping the browser

    Scenario: Stop Selenium
      Given I am stopping the selenium server
