package omar.cucumber.frontend.test

import omar.cucumber.frontend.test.TomcatStart

class CucumberTest {
    public void startTest() {
        String main_path = 'omar/cucumber/step_definitions'
        String resource_path = 'src/main/resources'

        if (System.getProperty("mainPath"))
        {
            main_path = System.getProperty("mainPath")
        }

        if (System.getProperty("resourcePath"))
        {
            resource_path = System.getProperty("resourcePath")
        }

        String[] arguments = [
                "--tags",
                "@o2_ui_search, @tlv_ui",
                "--tags",
                "~@C2S",
                '--plugin', "json:src/main/groovy/omar/webapp/reports/json/frontend.json",
                '--plugin', "html:src/main/groovy/omar/webapp/reports/html",
                '--plugin', "pretty",
                '--glue', main_path,
                resource_path]

        SecurityManagerCheck secManager = new SecurityManagerCheck()
        System.setSecurityManager(secManager)
        
        try {
            cucumber.api.cli.Main.main(arguments)
        } catch (SecurityException e) {
            //put what you want to happen when exit is called
        }
    }
}