## Dependencies
- Java
- Maven
- Selenium WebDriver
- TestNG
- Log4j
- Allure

## Before run
1. Add appropriate webdriver (chromedriver or geckodriver):
- for running on mac OS to package: {project_folder}/src/main/resources/driver/mac  
- for running on win OS to package: {project_folder}/src/main/resources/driver/win
2. For select browser for tests running, properties file {project_folder}/src/main/resources/project_data.properties needs to be changed:
- for running on Mozilla: driver=firefox
- for running on Chrome: driver=chrome

## How to run
1. Execute the command in terminal to run all tests:
mvn test
2. Execute the command in terminal to generate Allure report:
allure serve allure-results
*But Allure needs to be installed before (https://docs.qameta.io/allure/)

## Properties in project_data.properties
- *start.url* - url of application under test
- *driver* - browser name which will be used for test run
- *chromedriver.path.win* - path to chromedriver for win OS
- *chromedriver.path.mac* - path to chromedriver for mac OS
- *firefox.path.win* - path to geckodriver for win OS
- *firefox.path.mac* - path to geckodriver for mac OS
- *log.config.file* - path to log4j configuration file
- *timeouts.page* - timeout for pages loading
- *timeouts.element* - default timeout for loading elements
- *timeouts.minimal* - minimal timeout for loading elements (use if default timeout for loading elements is too long)