package com.beton.aqa.logging;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Verify {

    protected static final String VERIFICATION_MESSAGE = "%s, actual: '%s', expected: '%s'";
    protected static final Logger LOGGER = LogManager.getRootLogger();

    @Step("Verification: {successMessage}")
    public static void assertEquals(String actual, String expected, String successMessage) {
        LOGGER.debug(String.format(VERIFICATION_MESSAGE, successMessage, actual, expected));
        Assert.assertEquals(actual, expected);
        ScreenshotMaker.makeScreenshot();
    }

    @Step("Verification: {successMessage}")
    public static void assertTrue(boolean actualCondition, String successMessage) {
        LOGGER.debug(String.format(VERIFICATION_MESSAGE, successMessage, actualCondition, Boolean.TRUE));
        Assert.assertTrue(actualCondition, successMessage);
        ScreenshotMaker.makeScreenshot();
    }
}
