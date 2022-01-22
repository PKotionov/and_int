package com.beton.aqa.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static com.beton.aqa.data.Global.WEB_DRIVER_TYPE;
import static com.beton.aqa.utils.Timeouts.ELEMENT_TIMEOUT;
import static com.beton.aqa.utils.Timeouts.PAGE_LOAD_TIMEOUT;

public class DriverFactory {

    static WebDriver launchDriver() {
        WebDriver driver;
        if (WEB_DRIVER_TYPE.equalsIgnoreCase(BrowserType.GOOGLECHROME)) {
            driver = ChromeLauncher.createDriver();
        } else if (WEB_DRIVER_TYPE.equalsIgnoreCase(BrowserType.FIREFOX)) {
            driver = FirefoxLauncher.createDriver();
        } else {
            // Selenoid
            driver = SelenoidLauncher.createDriver();
        }

        driver.manage()
              .timeouts()
              .pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage()
              .timeouts()
              .implicitlyWait(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage()
              .timeouts()
              .setScriptTimeout(ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage()
              .window()
              .maximize();
        return driver;
    }
}
