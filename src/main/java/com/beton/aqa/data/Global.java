package com.beton.aqa.data;

import com.beton.aqa.utils.PropertyHelper;

public class Global {

    public static final String OS_NAME = getPropertyValue("os.name");
    public static final String WEB_DRIVER_TYPE = getPropertyValue("driver");
    public static final String CHROME_VERSION = getPropertyValue("chrome.version");

    public static final String USERNAME = getPropertyValue("user.username");
    public static final String PASSWORD = getPropertyValue("user.password");

    private static String getPropertyValue(String key) {
        return System.getProperty(key, PropertyHelper.getProperty(key));
    }
}