# omar-cucumber-frontend-test

### Purpose

The purpose of the omar-frontend-tests is to provide a suite of automated tests for different OMAR frontend/User Interface services. These tests run nightly and when certain OMAR projects are built. The current status of the tests helps developers to ensure that OMAR's User Interface services are running correctly.

### Content

The omar-frontend-tests are composed of 3 sets of tests, each for a different service.

These tests are made using Cucumber, a test language that resembles basic English. This allows anyone to look at the tests and understand their purpose and intent. Using the Cucumber tests as a guide, the tests are then converted into code. In the case of the frontend tests, Selenium is used to programatically control Firefox and Chrome browsers to test the UI services.

- *TLV-UI* - tests that the Time Lapse Viewer UI is working as intended using Selenium implementations of the Cucumber tests.
- *ImageSearch-UI* - tests that the ImageSearch UI is working as intended using Selenium implementations of the Cucumber tests.
- *Swagger-UI* - tests that the Swagger API pages exist for the appropriate applications using Selenium implementations of the Cucumber tests.

More details on the content of the tests can be found in the Cucumber *.feature* files located in *src/cucumber/resources/features*

### Automated Execution

The frontend tests are automatically executed using a Jenkins build on https://jenkins.ossim.io - the job name is omar-frontend-tests-dev for the dev branch and omar-frontend-tests-stage for the master branch. The tests run nightly.

### Manual Execution

Running the frontend tests requires 3 things: Gradle, the ChromeDriver/FirefoxDriver, and a compatible version of Chrome/Firefox. With those installed, simply run the *gradle frontend* command from the repository's base directory to execute the *frontend* task specified in the Gradle Build file.

The default config file uses the dev deployment of OMAR located at https://omar-dev.ossim.io

# Quickstart

## Gradle Commands

To run frontend test in Jenkins or locally
 ```
gradle run -PrunEnvironment="local"
 ```

To build the fat jar
 ```
gradle
 ```

To create dockerfile
 ```
gradle createDockerfile
 ```

 To build the docker image
 ```
gradle buildImage
 ```

## Run Commands

To run the docker image
```
docker run -p 8080:8080 omar-cucumber-frontend-test:<tag>
```
To run the Fat Jar (jars must be at the same level of src directory)
```
java -jar <path to jar>/omar-cucumber-frontend-test-<version>.jar
```
or
```
java -server -Xms256m -Xmx5400m -Djava.awt.headless=true -XX:+CMSClassUnloadingEnabled -XX:+UseGCOverheadLimit -Djava.security.egd=file:/dev/./urandom -Dwebdriver.gecko.driver=<path to geckodriver>geckodriver -jar <path to jar>/omar-cucumber-frontend-test-<version>.jar
```

## Openshift Environment Variables
### Overides the values in the cucumber config file
- TARGET_DEPLOYMENT
- DOMAIN_NAME
- RBT_CLOUD_ROOT_DIR

## Apps Needed
- geckodriver (the one in the repo is for Linux evironments)
- firefox (if you run locally)
- java (if you run locally)