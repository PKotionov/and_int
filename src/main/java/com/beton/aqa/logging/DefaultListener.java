package com.beton.aqa.logging;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class DefaultListener implements ITestListener {

    private final Logger logger = LogManager.getRootLogger();

    @Override
    public void onTestStart(ITestResult result) {
        logger.debug("Test started " + getTestMethodName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        saveTextLog(getTestMethodName(result) + " test passed success");
        ScreenshotMaker.makeScreenshot();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveTextLog(getTestMethodName(result) + " failed");
        ScreenshotMaker.makeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}