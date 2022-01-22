package com.beton.aqa.logging;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;

import static com.beton.aqa.logging.Verify.VERIFICATION_MESSAGE;

public class SoftVerify {

    private final Logger logger = LogManager.getRootLogger();
    private SoftAssert softAssert;


    public SoftVerify() {
        this.softAssert = new SoftAssert();
    }

    @Step("Verification: {successMessage}")
    public void verifyEquals(String actual, String expected, String successMessage) {
        logger.info(String.format(VERIFICATION_MESSAGE, successMessage, actual, expected));
        softAssert.assertEquals(actual, expected);
        ScreenshotMaker.makeScreenshot();
    }

    @Step("Verification: {successMessage}")
    public void verifyEquals(BigDecimal actual, BigDecimal expected, String successMessage) {
        logger.info(String.format(VERIFICATION_MESSAGE, successMessage, actual, expected));
        softAssert.assertEquals(actual, expected);
        ScreenshotMaker.makeScreenshot();
    }

    @Step("Verification: {successMessage}")
    public void verifyNotEquals(String actual, String expected, String successMessage) {
        logger.info(String.format(VERIFICATION_MESSAGE, successMessage, actual, expected));
        softAssert.assertNotEquals(actual, expected);
        ScreenshotMaker.makeScreenshot();
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}