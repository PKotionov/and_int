package com.gmail.pkotionov.driver;

import com.gmail.pkotionov.utils.PropertyHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.gmail.pkotionov.data.Global.OS_NAME;
import static com.gmail.pkotionov.data.Global.OS_NAME_WIN;

public class FirefoxLauncher {

    private static final String WEBDRIVER_GECKODRIVER = "webdriver.gecko.driver";
    private static final String WEBDRIVER_GECKO_EXE_PATH = OS_NAME.contains(OS_NAME_WIN)
            ? PropertyHelper.getProperty("firefox.path.win")
            : PropertyHelper.getProperty("firefox.path.mac");

    public static WebDriver createDriver() {
        System.setProperty(WEBDRIVER_GECKODRIVER, WEBDRIVER_GECKO_EXE_PATH);
        return new FirefoxDriver();
    }
}