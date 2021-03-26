package com.gmail.pkotionov.logging;

import com.gmail.pkotionov.driver.DriverProvider;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotMaker {

    private ScreenshotMaker() {
    }

    public static void makeScreenshot(){
        new ScreenshotMaker().saveScreenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        WebDriver driver = DriverProvider.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}