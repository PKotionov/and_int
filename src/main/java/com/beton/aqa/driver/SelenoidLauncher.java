package com.beton.aqa.driver;

import com.beton.aqa.utils.PropertyHelper;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static com.beton.aqa.data.Global.CHROME_VERSION;

public class SelenoidLauncher {

    private static final String SELENOID_URI = "http://localhost:4444/wd/hub";
    private static final String BROWSER_NAME = PropertyHelper.getProperty("selenoid.browser.name");

    public static RemoteWebDriver createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", BROWSER_NAME);
        capabilities.setCapability("browserVersion", CHROME_VERSION);
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));
        try {
            return new RemoteWebDriver(
                    URI.create(SELENOID_URI)
                       .toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error during Selenoid driver starting: " + e.getMessage());
        }
    }
}