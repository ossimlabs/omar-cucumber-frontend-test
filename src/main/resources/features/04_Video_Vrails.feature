@video_vrails
Feature: Video Vrails Interface?
  Is it working?

  Scenario: Start selenium
    Given I am starting the video vrails selenium server

  Scenario: Start browsers
    Given I am creating the firefox browser
    Given that I am starting at the video-vrails homepage using firefox

  Scenario: Click button
    Given I have clicked the menu button while the menu is NOT displayed
    Then The menu should be displayed
