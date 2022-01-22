package com.beton.aqa.driver;

import com.beton.aqa.utils.PropertyHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.beton.aqa.data.Global.OS_NAME;

public class FirefoxLauncher {

    private static final String WEBDRIVER_GECKODRIVER = "webdriver.gecko.driver";
    private static final String OS_NAME_WIN = "Win";
    private static final String WEBDRIVER_GECKO_EXE_PATH = OS_NAME.contains(OS_NAME_WIN)
                                                           ? PropertyHelper.getProperty("firefox.path.win")
                                                           : PropertyHelper.getProperty("firefox.path.mac");

    public static WebDriver createDriver() {
        System.setProperty(WEBDRIVER_GECKODRIVER, WEBDRIVER_GECKO_EXE_PATH);
        return new FirefoxDriver();
    }
}