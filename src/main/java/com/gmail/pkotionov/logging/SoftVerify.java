package com.gmail.pkotionov.logging;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;

public class SoftVerify {

    private SoftAssert softAssert;

    public SoftVerify() {
        this.softAssert = new SoftAssert();
    }

    @Step("Verification: {successMessage}")
    public void assertEquals(String actual, String expected, String successMessage) {
        softAssert.assertEquals(actual, expected);
        ScreenshotMaker.makeScreenshot();
    }

    @Step("Verification: {successMessage}")
    public void assertEquals(BigDecimal actual, BigDecimal expected, String successMessage) {
        softAssert.assertEquals(actual, expected);
        ScreenshotMaker.makeScreenshot();
    }

    @Step("Verification: {successMessage}")
    public void assertNotEquals(String actual, String expected, String successMessage) {
        softAssert.assertNotEquals(actual, expected);
        ScreenshotMaker.makeScreenshot();
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}