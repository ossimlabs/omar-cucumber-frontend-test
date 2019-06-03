@o2_ui_search
Feature: O2MapUserInterface
  As an analyst I need to be able to discover imagery in a user interface to choose for analysis

 # Scenario: Start
 #   Given I am starting the image search selenium server

  Scenario: [MAP-01] UI Map Search
    Given that I am starting at the O2 Home page
    When the Map button is clicked
    Then the Search page is loaded

#  Scenario: [MAP-02] UI Place Name Search
#    Given starting at O2 Home page#
#    When enter Melbourne in the place name
#    Then show map page centered on Melbourne

#  Scenario: [MAP-03] UI call to filter for an image by ImageID
#    Given on the Image Search Map page
#    And all filters all turned off
#    When Search filter ImageID set to 05FEB09OV05010005V090205M0001912264B220000100072M_001508507
#    And a search is performed
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario: [MAP-04] UI call to search for an image by bounding box in Decimal Degrees
#    Given on the Image Search Map page
#    And all filters all turned off
#    When spatial filter set to bounding box in Decimal Degrees
#    When a search is performed
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario: [MAP-05] UI call to search for an image by bounding box in DMS
#    Given on the Image Search Map page
#    And all filters all turned off
#    When spatial filter set to bounding box in DDMS
#    And a search is performed
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario: [MAP-06] UI call to search for an image by bounding box in MGRS
#    Given on the Image Search Map page
#    And all filters all turned off
#    When spatial filter set to bounding box in MGSR
#    And a search is performed
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario:[MAP-07] UI call to search for an image by Center Point & Radius in DMS
#    Given on the Image Search Map page
#    And all filters all turned off
#    When spatial filter set to center point radius in DMS
#    And a search is performed
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario: [MAP-08] Image search returns metadata
#    Given search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507
#    When image metadata is selected
#    Then image metadata discplayed matches expected results

#  Scenario: [MAP-09] UI call to search for an image by BE Number
#    Given on the Image Search Map page
#    And all filters all turned off
#    When  filter set to BE Number xxxx
#    And a search is performed
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario: [MAP-10] UI call to search for an image by Place Name
#    Given on the Image Search Map page
#    And all filters all turned off
#    When  filter set to Place Name xxxx
#    And a search is performed
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario: [MAP-11] Search by date/time
#    Given on the Image Search Map page
#    When spatial filters all turned off
#    And start date set to xxxxx
#    And end date set to xxxxx
#    Then search results displays image 05FEB09OV05010005V090205M0001912264B220000100072M_001508507

#  Scenario: Stop
#    Given I am stopping the image search selenium server