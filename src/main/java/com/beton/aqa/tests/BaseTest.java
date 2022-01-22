package com.beton.aqa.tests;

import com.beton.aqa.driver.DriverProvider;
import com.beton.aqa.logging.DefaultListener;
import com.beton.aqa.logging.SoftVerify;
import com.beton.aqa.utils.PropertyHelper;
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
    protected SoftVerify softVerify;

    @BeforeSuite
    public void beforeSuit() {
        System.setProperty("log4j.configurationFile", PropertyHelper.getProperty("log.config.file"));
    }

    @BeforeClass
    public void beforeClass() {
        softVerify = new SoftVerify();
        WebDriver driver = DriverProvider.getDriver();
        logger.debug("Open url: " + START_URL);
        driver.get(START_URL);
    }

    @AfterClass
    public void afterClass() {
        logger.debug("Tear down driver");
        DriverProvider.tearDown();
        softVerify.assertAll();
    }
}
