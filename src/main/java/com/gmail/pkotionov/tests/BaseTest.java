package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.driver.DriverProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected final Logger logger = LogManager.getRootLogger();

    private final String START_URL = "https://www.paysera.lt/v2/en-LT/fees/currency-conversion-calculator#/";

    @BeforeSuite
    public void beforeSuit(){
        System.setProperty("log4j.configurationFile", "src\\main\\resources\\log4j2.xml");
    }

    @BeforeClass
    public void beforeClass() {
        WebDriver driver = DriverProvider.getDriver();
        logger.info("Open url: " + START_URL);
        driver.get(START_URL);
    }

    @AfterSuite
    public void afterSuit() {
        logger.info("Tear down driver");
        DriverProvider.tearDown();
    }
}
