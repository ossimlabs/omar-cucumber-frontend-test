@swagger_ui
Feature: SwaggerUserInterface

# Scenario: Start
#    Given I am starting the swagger ui selenium server

  Scenario: [Swagger-01] MENSA Documentation is available
    Given that the mensa swagger page is available for mensa
    Then the mensa groundToImagePoints, imageDistance, imagePointsToGround param documentation should be present for mensa

  Scenario: [Swagger-02] WFS Documentation is available
    Given that the wfs swagger page is available for wfs
    Then the wfs describeFeatureType, getCapabilities, getFeature param documentation should be present for wfs

  Scenario: [Swagger-03] WMS Documentation is available
    Given that the wms swagger page is available for wms
    Then the wms getCapabilities, getMap param documentation should be present for wms

  Scenario: [Swagger-04] WCS Documentation is available
    Given that the wcs swagger page is available for wcs
    Then the wcs getCapabilities, getCoverage, describeCoverage param documentation should be present for wcs

  Scenario: [Swagger-05] WMTS Documentation is available
    Given that the wmts swagger page is available for wmts
    Then the wmts getCapabilities, getTile, layers param documentation should be present for wmts

  Scenario: [Swagger-06] GeoscriptApi Documentation is available
    Given that the geoscriptApi swagger page is available for geoscript
    Then the geoscriptApi getCapabilitiesData, getSchemaInfoByTypeName, listProjections, queryLayer param documentation should be present for geoscriptApi


  Scenario Outline: [Swagger-07] OMS Documentation is available
    Given that the <swaggerEndpoint> swagger page is available for <swaggerApp>
    Then the <swaggerEndpointSubName> <params> param documentation should be present for <swaggerEndpoint>
    Examples:
      | swaggerApp | swaggerEndpoint | swaggerEndpointSubName | params                                |
      | oms        | dataInfo        | dataInfo               | getInfo                               |
      | oms        | chipper         | chipper                | chip, ortho                           |
      | oms        | imageSpace      | imageSpace             | getThumbnail, getTile, getTileOverlay |

  Scenario Outline: [Swagger-08] Stager Documentation is available
    Given that the <swaggerEndpoint> swagger page is available for <swaggerApp>
    Then the <swaggerEndpointSubName> <params> param documentation should be present for <swaggerEndpoint>
    Examples:
      | swaggerApp | swaggerEndpoint | swaggerEndpointSubName | params                                                                               |
      | stager     | rasterDataSet   | dataManager            | addRaster, getDistinctValues, getRasterFiles, getRasterFilesProcessing, removeRaster |
      | stager     | videoDataSet    | dataManager            | addVideo, removeVideo                                                                |

  Scenario Outline: [Swagger-09] AVRO Documentation is available
    Given that the <swaggerEndpoint> swagger page is available for <swaggerApp>
    Then the <swaggerEndpointSubName> <params> param documentation should be present for <swaggerEndpoint>
    Examples:
      | swaggerApp | swaggerEndpoint | swaggerEndpointSubName | params                                                                                            |
      | avro       | avro            | avro                   | addFile, addMessage, listFiles, listMessages, resetFileProcessingStatus                           |
      | avro       | ingestMetrics   | ingestMetrics          | delete, endCopy, endIngest, endStaging, list, save, startCopy, startIngest, startStaging, summary |

  Scenario Outline: [Swagger-10] Download Documentation is available
    Given that the <swaggerEndpoint> swagger page is available for <swaggerApp>
    Then the <swaggerEndpointSubName> <params> param documentation should be present for <swaggerEndpoint>
    Examples:
      | swaggerApp | swaggerEndpoint | swaggerEndpointSubName | params   |
      | download   | archive         | archive                | download |

#  Scenario: Stop
#    Given I am stopping the swagger ui selenium server