package com.gmail.pkotionov.driver;

import com.gmail.pkotionov.utils.PropertyHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.gmail.pkotionov.data.Global.OS_NAME;
import static com.gmail.pkotionov.data.Global.OS_NAME_WIN;

public class ChromeLauncher {

    private static final String WEBDRIVER_CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROME_EXE_PATH = OS_NAME.contains(OS_NAME_WIN)
            ? PropertyHelper.getProperty("chromedriver.path.win")
            : PropertyHelper.getProperty("chromedriver.path.mac");

    public static WebDriver createDriver() {
        System.setProperty(WEBDRIVER_CHROMEDRIVER, WEBDRIVER_CHROME_EXE_PATH);
        return new ChromeDriver();
    }
}