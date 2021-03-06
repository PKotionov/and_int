package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.driver.DriverProvider;
import com.gmail.pkotionov.logging.DefaultListener;
import com.gmail.pkotionov.utils.PropertyHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(DefaultListener.class)
public class BaseTest {

    protected final Logger logger = LogManager.getRootLogger();

    private final String START_URL = PropertyHelper.getProperty("start.url");

    @BeforeSuite
    public void beforeSuit() {
        System.setProperty("log4j.configurationFile", PropertyHelper.getProperty("log.config.file"));
    }

    @BeforeClass
    public void beforeClass() {
        WebDriver driver = DriverProvider.getDriver();
        logger.info("Open url: " + START_URL);
        driver.get(START_URL);
    }

    @AfterClass
    public void afterClass() {
        logger.info("Tear down driver");
        DriverProvider.tearDown();
    }
}
