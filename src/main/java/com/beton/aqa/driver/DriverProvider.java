package com.beton.aqa.driver;

import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static WebDriver driver;

    private DriverProvider() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverFactory.launchDriver();
        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
