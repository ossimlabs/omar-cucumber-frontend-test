package omar.cucumber.step_definitions

import geb.Browser
import org.openqa.selenium.firefox.FirefoxDriver
import omar.cucumber.config.CucumberConfig

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


def config = CucumberConfig.config
remoteDisplay = null

/*
Given(~/^I am starting the swagger ui selenium server$/) {
    ->
        println "Starting remote display..."
        def command = ["Xvfb", ":1", "-screen", "0", "1366x768x24", "-ac"]
        remoteDisplay = command.execute()

        println "Starting browser..."
        browser = new Browser(driver: new FirefoxDriver())
}

Given(~/^I am stopping the swagger ui selenium server$/) {
    ->
        println "Stopping browser..."
        browser.quit()

        println "Stopping remote display..."
        remoteDisplay.waitForOrKill(1)
}*/

Given(~/^that the (.*) swagger page is available for (.*)$/) { String swaggerEndpoint, String swaggerApp ->
    def url = "${config[swaggerApp + 'Url']}/api#!/${swaggerEndpoint}"
    browser.go(url)

    def pageTitle = browser.getTitle()
    println "Page Title: ${pageTitle} for ${swaggerApp}, endpoint ${swaggerEndpoint}"
    assert pageTitle == "Swagger UI"
}

Then(~/^the ([^\s]*) (.*) param documentation should be present for (.*)$/) { String swaggerEndpointSubName, String params, String swaggerEndpoint ->
    params.split(",").each {
        def param = it.trim()

        // give time for the page to gather all its resources
        sleep(1200)
        // click the hyperlink
        browser.page.$("a", text: "/${swaggerEndpointSubName}/${param}").click()
        // give some time for the box to display
        sleep(750)
        // make sure the documentation box is visible
        println "Checking that ${param} Swagger API exists for ${swaggerEndpoint}"
        assert browser.page.$("div", id: "${swaggerEndpoint}_${param}_content").displayed == true
    }
}
