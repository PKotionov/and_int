package com.gmail.pkotionov.utils;

import com.gmail.pkotionov.driver.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    public static boolean waitForElementPresence(WebElement parentElement, By elementLocator, int timeout) {
        return (new WebDriverWait(DriverProvider.getDriver(), timeout))
                .until((ExpectedCondition<Boolean>) d ->
                        parentElement.findElements(elementLocator).size() > 0
                );
    }

    public static WebElement waitForElementPresence(WebElement element, int timeout) {
        return new WebDriverWait(DriverProvider.getDriver(), timeout)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean waitForElementStayInvisible(WebElement element, int timeout) {
        return new WebDriverWait(DriverProvider.getDriver(), timeout)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean waitForElementHasText(WebElement element, int timeout) {
        return (new WebDriverWait(DriverProvider.getDriver(), timeout))
                .until((ExpectedCondition<Boolean>) d -> !element.getText().isEmpty());
    }
}
