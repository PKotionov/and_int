package com.beton.aqa.tests;

import com.beton.aqa.logging.DefaultListener;
import com.beton.aqa.logging.SoftVerify;
import com.beton.aqa.pages.HighlightsPage;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(DefaultListener.class)
public class LoginTest extends BaseTest {

    @Test
    @Description("Log in")
    public void loginTest() {
        HighlightsPage highlightsPage = new HighlightsPage();
        highlightsPage.login();
    }
}
