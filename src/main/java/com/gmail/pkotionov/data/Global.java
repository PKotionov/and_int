package com.gmail.pkotionov.data;

import com.gmail.pkotionov.utils.PropertyHelper;

public class Global {

    public static final String OS_NAME = System.getProperty("os.name");
    public static final String WEBDRIVER_TYPE = PropertyHelper.getProperty("driver");

    public static final String CHROME_NAME = "Chrome";
    public static final String OS_NAME_WIN = "Win";

}