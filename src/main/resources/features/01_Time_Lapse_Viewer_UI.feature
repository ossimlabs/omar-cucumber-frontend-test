@tlv_ui
Feature: TLVUserInterface

# O2SVC-710 (U) OC2S shall provide the capability for analsyts to build a Time Lapse Viewer (TLV) flipbook product within a specified date range
#  Scenario: TLV acquisition date search
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near Paris, France
#    Then all images should be within the date range specified

  Scenario: Start selenium
    Given I am starting the tlv ui selenium server

  Scenario: Start browsers
    Given I am creating the tlv browsers

  Scenario: TLV acquisition date search
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near Paris, France
    Then all images should be within the date range specified

# O2SVC-757 (U) OC2S GUI shall provide a user with the option to add and view annotations.
#  Scenario: TLV annotations
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near Lenah Valley, Australia
#    And I add a circle annotation
#    And I add a line annotation
#    And I add a point annotation
#    And I add a polygon annotation
#    And I add a rectangle annotation
#    And I add a square annotation
#    Then the image displays the annotations

  Scenario: TLV annotations
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near Lenah Valley, Australia
    And I add a circle annotation
    And I add a line annotation
    And I add a point annotation
    And I add a polygon annotation
    And I add a rectangle annotation
    And I add a square annotation
    Then the image displays the annotations

# O2SVC-687 (U) OC2S shall support TLV operation using the control buttons at the bottom, or using hot keys including the arrow keys for skipping forward and backward
# O2SVC-706 (U) OC2S shall provide the capability to provide quick image QC (e.g. clouds, dithering)
# O2SVC-776 (U) OC2S shall provide image stacking
#  Scenario: TLV arrow key controls
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near Paris, France
#    Then I can use the arrow keys to cycle through the stack

  Scenario: TLV arrow key controls
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near Paris, France
    Then I can use the arrow keys to cycle through the stack

# O2SVC-704 (U) OC2S shall support filtering via TLV using the Delete key, which simply removes the image from the current stack
# O2SVC-711 (U) OC2S shall provide the capability for users to permanently remove an image from the Time Lapse Viewer product without removing any corresponding image chips from the report
#  Scenario: TLV delete key control
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near Paris, France
#    Then I can use the delete key to remove an image from the stack

  Scenario: TLV delete key control
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near Paris, France
    Then I can use the delete key to remove an image from the stack

# O2SVC-755 (U) OC2S GUI shall provide a user with the option to adjust an image from the results page
# O2SVC-716 (U) OC2S shall provide image chipping
#  Scenario: TLV image adjustments
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near Paris, France
#    And I adjust the bands of a layer
#    And I adjust the DRA region of a layer
#    And I adjust the interpolation of a layer
#    And I adjust the DRA of a layer
#    And I adjust the sharpness of a layer
#    And I adjust the brightness of a layer
#    And I adjust the contrast of a layer
#    Then the layer's image pixels change

  Scenario: TLV image adjustments
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near Paris, France
    And I adjust the sharpness of a layer
    And I adjust the brightness of a layer
    And I adjust the contrast of a layer
    #And I adjust the bands of a layer
    #And I adjust the DRA region of a layer
    #And I adjust the interpolation of a layer
    #And I adjust the DRA of a layer
    Then the layer's image pixels change

# O2SVC-734 (U) OC2S user interfaces shall run in a web browser
# O2SVC-749 (U) OC2S shall provide the capability for users to export animations as URL links
#  Scenario: TLV REST API
#    Given that I am starting at the TLV home page using Chrome
#    When endDay=01, endHour=02, endMinute=03, endMonth=04, endSecond=05, endYear=2010, maxCloudCover=50, maxResults=25, minNiirs=6, startDay=07, startHour=08, startMinute=09, startMonth=10, startSecond=11, startYear=2000 are supplied in the TLV URL
#    Then the search dialog will show a Start Date of 10/07/2000 08:09:11, an End Date of 04/01/2010 02:03:05, a Min. NIIRS of 6, a Max. Cloud Cover of 50, and Max Results of 25

  Scenario: TLV REST API
    Given that I am starting at the TLV home page using Firefox
    When endDay=01, endHour=02, endMinute=03, endMonth=04, endSecond=05, endYear=2010, maxCloudCover=50, maxResults=25, minNiirs=6, startDay=07, startHour=08, startMinute=09, startMonth=10, startSecond=11, startYear=2000 are supplied in the TLV URL
    Then the search dialog will show a Start Date of 10/07/2000 08:09:11, an End Date of 04/01/2010 02:03:05, a Min. NIIRS of 6, a Max. Cloud Cover of 50, and Max Results of 25

# O2SVC-697 (U) OC2S shall provide the capability to animate chips to create a time Lapse view of targets
# O2SVC-744 (U) OC2S shall provide the TLV Web User Interface
#  Scenario: TLV search by Decimal Degrees
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near -23.591080, 138.980708
#    Then I get images that contain -23.591080, 138.980708

  Scenario: TLV search by Decimal Degrees
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near -23.591080, 138.980708
    Then I get images that contain -23.591080, 138.980708

#  Scenario: TLV search by Degrees Minutes Seconds
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near 23° 35' 04.41" S 138° 59' 57.72" E
#    Then I get images that contain -23.584557, 138.999367

  Scenario: TLV search by Degrees Minutes Seconds
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near 23° 35' 04.41" S 138° 59' 57.72" E
    Then I get images that contain -23.584557, 138.999367

#  Scenario: TLV search by MGRS
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near 55GEN2127254539
#    Then I get images that contain -42.861443, 147.260399

  Scenario: TLV search by MGRS
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near 55GEN2127254539
    Then I get images that contain -42.861443, 147.260399

#  Scenario: TLV search by Placename
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near Paris, France
#    Then I get images that contain 48.8584, 2.2945

  Scenario: TLV search by Placename
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near Paris, France
    Then I get images that contain 48.8584, 2.2945

# O2SVC-715 (U) OC2S shall support viewing of image stack metadata can be viewed by clicking the "Export Time Lapse Summary" button
#  Scenario: TLV summary table
#    Given that I am starting at the TLV home page using Chrome
#    When I search for imagery near Paris, France
#    And I click the Summary Table button
#    Then a table appears with the stack's metadata

  Scenario: TLV summary table
    Given that I am starting at the TLV home page using Firefox
    When I search for imagery near Paris, France
    And I click the Summary Table button
    Then a table appears with the stack's metadata

  Scenario: Remove browsers
    Given I am closing the tlv browsers
