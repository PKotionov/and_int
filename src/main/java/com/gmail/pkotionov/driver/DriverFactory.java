package com.gmail.pkotionov.driver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.gmail.pkotionov.data.Global.CHROME_NAME;
import static com.gmail.pkotionov.data.Global.WEBDRIVER_TYPE;
import static com.gmail.pkotionov.utils.TimeOuts.ELEMENT_TIMEOUT;
import static com.gmail.pkotionov.utils.TimeOuts.PAGE_LOAD_TIMEOUT;

public class DriverFactory {

    static WebDriver launchDriver() {
        WebDriver driver;
            if(WEBDRIVER_TYPE.equalsIgnoreCase(CHROME_NAME)){
                driver = ChromeLauncher.createDriver();
            } else {
                driver = FirefoxLauncher.createDriver();
            }

        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
