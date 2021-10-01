package com.beton.aqa.data;

import com.beton.aqa.utils.PropertyHelper;

public class Global {

    public static final String OS_NAME = System.getProperty("os.name");
    public static final String WEBDRIVER_TYPE = PropertyHelper.getProperty("driver");
    public static final String CHROME_VERSION = PropertyHelper.getProperty("chrome.version");
    public static final String OS_NAME_WIN = "Win";

    public static final String USERNAME = PropertyHelper.getProperty("user.username");
    public static final String PASSWORD = PropertyHelper.getProperty("user.password");

}