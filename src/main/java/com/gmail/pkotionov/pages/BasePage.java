package com.gmail.pkotionov.pages;

import com.gmail.pkotionov.driver.DriverProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected final Logger logger = LogManager.getRootLogger();

    public BasePage() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }
}
