package omar.cucumber.step_definitions

import geb.Browser
import groovy.json.JsonOutput
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.Keys
import omar.cucumber.config.CucumberConfig


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


def config = CucumberConfig.config
def homePageUrl = config.tlvUrl
def imageProperties = []
boolean browserCreated = false

def firefoxBrowser
def chromeBrowser

buildNumber = System.getenv("BUILD_NUMBER")
videoPrefix = buildNumber != null ? "${buildNumber}_" : ""


Given(~/^I am starting the tlv ui selenium server$/) {
    ->
        println "Starting remote display..."
        def command = ["Xvfb", ":1", "-screen", "0", "1366x768x24", "-ac"]
        remoteDisplay = command.execute()
        sleep(3000)
        /*println "Starting VNC server..."
        try {
            command = ["x11vnc", "-display", ":1", "-localhost", "-shared", "-forever"]
            command.execute()
            sleep(3000)
        } catch (IOException e) {
            println('Starting VNC server failed...')
        }
        println "Starting video recording..."
        try {
            command = ["flvrec.py", "-o", "${videoPrefix}high_quality_video.flv", "localhost", "5900"]
            command.execute()
            sleep(3000)
        } catch (IOException e) {
            println('Starting video recording failed...')
        }*/
}

Given(~/^I am creating the tlv browsers$/) {
    ->

        // Create chromeBrowser
        // chromeBrowser = new Browser(driver: new ChromeDriver()); break;

        // Create firefoxBrowser
        def driver
        def file = new File( "blah" )//config.browsers.firefox.profile )
        if ( file.exists() ) {
            def profile = new FirefoxProfile( file )
            driver = new FirefoxDriver( profile )
        }
        else {
            driver = new FirefoxDriver()
        }
        firefoxBrowser = new Browser( driver: driver )
}


/*
Given(~/^I am stopping the tlv ui selenium server$/) {
    ->
        println "Stopping remote display..."
        remoteDisplay.waitForOrKill(1)
        sleep(3000)
}*/

Given(~/^I am closing the tlv browsers$/) {
    ->
        println "Stopping firefox browser..."
        firefoxBrowser.quit()
}

And(~/I add a (.*) annotation$/) {
    String annotation ->

        println "Adding ${annotation} annotation..."

        browser.driver.executeScript("return displayNavbar();")
        def annotationsButton = browser.page.$("body").find("a").find { it.@title == "Annotations" }
        annotationsButton.click()

        sleep(1000)

        def annotationButton = browser.page.$("body").find("a").find { it.text() == annotation.capitalize() }
        annotationButton.click()

        sleep(1000)

        def map = browser.page.$("#map")
        def random = new Random()
        def startX = map.width / 2 as Integer
        def startY = map.height / 2 as Integer
        def xMax = map.width - 50
        def xMin = 50
        def yMax = map.height - 50
        def yMin = 150

        def actions = new Actions(browser.driver)
        switch (annotation)
        {
            case "circle":
            case "rectangle":
            case "square":
                def x1 = random.nextInt(xMax - xMin) + xMin
                def y1 = random.nextInt(yMax - yMin) + yMin
                def x2 = random.nextInt(xMax - xMin) + xMin
                def y2 = random.nextInt(yMax - yMin) + yMin
                actions.moveToElement(map.firstElement()).moveByOffset(-startX, -startY).moveByOffset(x1, y1).perform()
                sleep(500)
                actions.click().perform()
                sleep(500)
                actions.moveByOffset(x2 - x1, y2 - y1).perform()
                sleep(500)
                actions.click().perform()
                //actions.moveToElement(map.firstElement()).moveByOffset(x1, y1).click().moveByOffset(x2, y2).click().perform()
                sleep(1000)
                break
            case "line":
            case "polygon":
                def x1 = random.nextInt(xMax - xMin) + xMin
                def y1 = random.nextInt(yMax - yMin) + yMin
                def x2 = random.nextInt(xMax - xMin) + xMin
                def y2 = random.nextInt(yMax - yMin) + yMin
                def x3 = random.nextInt(xMax - xMin) + xMin
                def y3 = random.nextInt(yMax - yMin) + yMin
                def x4 = random.nextInt(xMax - xMin) + xMin
                def y4 = random.nextInt(yMax - yMin) + yMin
                def x5 = random.nextInt(xMax - xMin) + xMin
                def y5 = random.nextInt(yMax - yMin) + yMin
                actions.moveToElement(map.firstElement()).moveByOffset(-startX, -startY).moveByOffset(x1, y1).click().moveByOffset(x2 - x1, y2 - y1).click().moveByOffset(x3 - x2, y3 - y2).click().moveByOffset(x4 - x3, y4 - y3).click().moveByOffset(x5 - x4, y5 - y4).click().perform()
                //actions.moveToElement(map.firstElement()).moveByOffset(x1, y1).click().moveByOffset(x2, y2).click().moveByOffset(x3, y3).click().perform()
                browser.driver.executeScript("return tlv.drawAnnotationInteraction.finishDrawing();")
                sleep(1000)
                break
            case "point":
                def x1 = random.nextInt(xMax - xMin) + xMin
                def y1 = random.nextInt(yMax - yMin) + yMin
                actions.moveToElement(map.firstElement()).moveByOffset(-startX, -startY).moveByOffset(x1, y1).perform()
                sleep(500)
                actions.click().perform()
                //actions.moveToElement(map.firstElement()).moveByOffset(x1, y1).click().perform()
                sleep(1000)
                break
        }

        imageProperties.push(getCanvasData())

        browser.page.$("#annotationsDialog").find("button")[2].click()
        sleep(1000)
}

And(~/I adjust the (.*) of a layer$/) {
    String imageProperty ->

        println "Adjusting ${imageProperty}"

        browser.driver.executeScript("return displayNavbar();")
        def imagePropertiesButton = browser.page.$("body").find("a").find { it.@title == "Image Properties" }
        imagePropertiesButton.click()

        switch (imageProperty)
        {
            case "bands":
                def select = browser.page.$("#selectBandsMethodSelect")
                def option = select.find("option").find { it.text() == "Manual" }
                browser.driver.executeScript("return syncImageProperties();") // gets the gun selects populated
                option.click()
                ["red", "green", "blue"].eachWithIndex {
                    value, index ->
                        select = browser.page.$("#${value}GunSelect")
                        option = select.find("option").find { it.text() == "${index + 2}" }
                        option.click()
                }
                break
            case "brightness":
                def slider = browser.page.$("#brightnessSlider")
                def track = slider.find(".slider-track-high")
                track.click()
                break
            case "contrast":
                def slider = browser.page.$("#contrastSlider")
                def track = slider.find(".slider-track-high")
                track.click()
                break
            case "DRA":
                def select = browser.page.$("#dynamicRangeSelect")
                def option = select.find("option").find { it.text() == "None" }
                option.click()
                break
            case "DRA region":
                def select = browser.page.$("#dynamicRangeRegionSelect")
                def option = select.find("option").find { it.text() == "Viewport" }
                option.click()
                break
            case "interpolation":
                def select = browser.page.$("#interpolationSelect")
                def option = select.find("option").find { it.text() == "Sinc" }
                option.click()
                break
            case "sharpness":
                def slider = browser.page.$("#sharpenSlider")
                def track = slider.find(".slider-track-high")
                track.click()
                break
        }

        browser.driver.executeScript("return updateImageProperties(true) ;")
        sleep(10000) // gives enough time for SOMETHING to change
        imageProperties.push(getCanvasData())
}

And(~/I click the Summary Table button$/) { ->
    browser.driver.executeScript("return buildSummaryTable();")
    browser.driver.executeScript("return \$( '#summaryTableDialog' ).modal( 'show' );")
    //def summaryTableButton = browser.page.$("div", class: "summary-table-control")
    //summaryTableButton.click()

    sleep(1000)
}

Given(~/^that I am starting at the TLV home page using (.*)$/) {
    String browserType ->
        println "Using ${browserType}"

        switch (browserType)
        {
            case "Chrome":
                browser = chromeBrowser
                break
            case "Firefox":
                browser = firefoxBrowser
                break
        }

        browser.go(homePageUrl)
        def pageTitle = browser.getTitle()


        assert pageTitle == "Time Lapse Viewer (TLV)"
}

Then(~/a table appears with the stack's metadata$/) { ->


    assert browser.page.$("#timeLapseSummaryTable").displayed == true
}

Then(~/all images should be within the date range specified$/) { ->
    def startDate = Date.parse("dd/MM/yyyy HH:mm:ss", browser.page.$("#searchStartDateTimePicker").children()[0].value())
    def endDate = Date.parse("dd/MM/yyyy HH:mm:ss", browser.page.$("#searchEndDateTimePicker").children()[0].value())

    def layers = browser.driver.executeScript("return tlv.layers.length;") as Integer
    (1..layers).each {
        def date = Date.parse("yyyy-MM-dd HH:mm:ss", browser.driver.executeScript("return tlv.layers[ ${it - 1} ].acquisitionDate;"))


        assert startDate.getTime() < date.getTime()
        assert endDate.getTime() > date.getTime()
    }
}

Then(~/I can use the arrow keys to cycle through the stack$/) { ->
    def layers = browser.driver.executeScript("return tlv.layers.length;") as Integer
    (1..layers).each {
        def imageId = browser.driver.executeScript("return tlv.layers[ ${it - 1} ].imageId;")
        assert browser.page.$("#imageIdDiv")[0].text().contains(imageId) == true

        def acquisitionDate = browser.driver.executeScript("return tlv.layers[ ${it - 1} ].acquisitionDate;")
        assert browser.page.$("#acquisitionDateDiv")[0].text().contains(acquisitionDate) == true

        assert browser.page.$("#tlvLayerCountSpan")[0].text() == "${it}/${layers}"
        browser.page.$("body") << Keys.ARROW_RIGHT

        sleep(1000)
    }

    (1..layers).reverse().each {
        browser.page.$("body") << Keys.ARROW_LEFT

        def imageId = browser.driver.executeScript("return tlv.layers[ ${it - 1} ].imageId;")
        assert browser.page.$("#imageIdDiv")[0].text().contains(imageId) == true

        def acquisitionDate = browser.driver.executeScript("return tlv.layers[ ${it - 1} ].acquisitionDate;")
        assert browser.page.$("#acquisitionDateDiv")[0].text().contains(acquisitionDate) == true

        assert browser.page.$("#tlvLayerCountSpan")[0].text() == "${it}/${layers}"

        sleep(1000)
    }
}

Then(~/I can use the delete key to remove an image from the stack$/) { ->

    def layersBeforeDelete = browser.driver.executeScript("return tlv.layers.length;") as Integer
    browser.page.$("body") << Keys.DELETE
    def layersAfterDelete = browser.driver.executeScript("return tlv.layers.length;") as Integer

    sleep(1000)

    assert layersBeforeDelete == layersAfterDelete + 1
    assert browser.page.$("#tlvLayerCountSpan")[0].text() == "1/${layersBeforeDelete - 1}"
}

Then(~/I can use the mouse to pan and zoom on the imagery$/) { ->
    println browser.driver.executeScript("return tlv.map.getView().calculateExtent( tlv.map.getSize() );")

    browser.driver.executeScript("return ol.interaction.MouseWheelZoom.handleEvent({ map: tlv.map, originalEvent: { deltaY: -120, preventDefault: function() {} }, preventDefault: ol.MapBrowserEvent.prototype.preventDefault, type: 'wheel' })")

    //def actions = new Actions( browser.driver )
    //def map = browser.driver.findElement(By.id("map")) //; browser.page.$( "#map" ).firstElement()
    //println map.properties
    //def pan = actions.moveToElement( map ).clickAndHold().moveByOffset(100, -100).release().build()
//def pan = actions.dragAndDropBy( map, 100, -100 ).build()
//def pan = actions.moveToElement( map ).doubleClick().build()
    //def pan = actions.clickAndHold( map ).moveByOffset(100, -100).release().build()
    //def pan = actions.doubleClick( map ).build()
//    pan.perform()


    sleep(1000)
    println browser.driver.executeScript("return tlv.map.getView().calculateExtent( tlv.map.getSize() );")
}

Then(~/I get images that contain (.*)$/) {
    String location ->

        def coordinate = location.split(",").reverse().collect { it as Double }
        def layers = browser.driver.executeScript("return tlv.layers ? tlv.layers.length : 0;") as Integer
        (1..layers).each {
            def geometry = JsonOutput.toJson(browser.driver.executeScript("return tlv.layers[ ${it - 1} ].metadata.footprint"))
            def extent = browser.driver.executeScript("return new ol.format.WKT().readGeometry( ${geometry} ).getExtent()")

            assert browser.driver.executeScript("return ol.extent.containsCoordinate( ${extent}, ${coordinate} )") == true
        }
}

Then(~/the image displays the annotations$/) { ->
    def allImagesAreDifferent = true
    imageProperties.eachWithIndex {
        canvasData1, index1 ->
            imageProperties.eachWithIndex {
                canvasData2, index2 ->
                    if (index1 != index2)
                    {
                        assert canvasData1 != canvasData2
                    }
            }
    }
}

Then(~/the layer's image pixels change$/) { ->
    def allImagesAreDifferent = true
    imageProperties.eachWithIndex {
        canvasData1, index1 ->
            imageProperties.eachWithIndex {
                canvasData2, index2 ->
                    if (index1 != index2)
                    {
                        assert canvasData1 != canvasData2
                    }
            }
    }
}

Then(~/^the search dialog will show a Start Date of (.*), an End Date of (.*), a Min. NIIRS of (.*), a Max. Cloud Cover of (.*), and Max Results of (.*)$/) {
    String startDate, String endDate, String minNiirs, String maxCloudCover, String maxResults ->

        def startDateValue = browser.page.$("#searchStartDateTimePicker").children()[0].value()
        assert startDate == startDateValue

        def endDateValue = browser.page.$("#searchEndDateTimePicker").children()[0].value()
        assert endDateValue == endDate

        def minNiirsValue = browser.page.$("#searchMinNiirsInput").value()
        assert minNiirsValue == minNiirs

        def maxResultsValue = browser.page.$("#searchMaxResultsSelect").value()
        assert maxResultsValue == maxResults

        println("Search dialogs verified")
}

When(~/(.*) are supplied in the TLV URL$/) {
    String params ->

        def url = homePageUrl + "?${params.split(", ").join("&")}"
        browser.go(url)
}

When(~/I search for imagery near (.*)$/) {
    String location ->

        println "Searching for imagery near ${location}..."

        browser.page.$("#searchStartDateTimePicker").children()[0].value("01/01/2000 00:00:00")
        sleep(1000)
        browser.page.$("#searchLocationInput").value(location)
        sleep(2000)

        browser.page.$("#searchDialog").find(".modal-footer").find(".btn-primary")[1].click()

        def layers
        // wait a maximum of 30 seconds for results to return
        def timer = 3
        while (timer > 0)
        {
            println new Date()
            sleep(10000)

            layers = browser.driver.executeScript("return tlv.layers ? tlv.layers.length : 0;") as Integer
            println "tlv.layers: ${ layers }"
            if (layers > 0)
            {
                timer = 0
            }
            else
            {
                timer -= 1
                if ( timer == 0 ) { println "Search timed out..." }
            }
        }

        assert layers > 0
}

def getCanvasData()
{
    browser.driver.executeScript(
            "tlv.map.once(" +
                    "'postcompose'," +
                    "function(event) {" +
                    "var canvas = event.context.canvas;" +
                    "tlv.canvasData = canvas.toDataURL();" +
                    "}" +
                    ");" +
                    "tlv.map.renderSync();"
    )

    return browser.driver.executeScript("return tlv.canvasData;")
}
