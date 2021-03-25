package com.gmail.pkotionov.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_GECKODRIVER_EXE_PATH = ".\\geckodriver\\geckodriver.exe";
    private static final String WEBDRIVER_CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROMEDRIVER_EXE_PATH = "src\\main\\resources\\driver\\chromedriver.exe";

    static WebDriver createDriver() {
        //todo add possibility to change driver
        System.setProperty(WEBDRIVER_CHROMEDRIVER, WEBDRIVER_CHROMEDRIVER_EXE_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
