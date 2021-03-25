package com.gmail.pkotionov.driver;

import com.gmail.pkotionov.utils.PropertyHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.gmail.pkotionov.utils.TimeOuts.ELEMENT_TIMEOUT;
import static com.gmail.pkotionov.utils.TimeOuts.PAGE_LOAD_TIMEOUT;

public class DriverFactory {

    private static final String WEBDRIVER_CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROMEDRIVER_EXE_PATH = PropertyHelper.getProperty("chromedriver.path");

    static WebDriver createDriver() {
        System.setProperty(WEBDRIVER_CHROMEDRIVER, WEBDRIVER_CHROMEDRIVER_EXE_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
