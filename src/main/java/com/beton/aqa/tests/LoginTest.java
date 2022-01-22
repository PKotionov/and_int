package com.beton.aqa.tests;

import com.beton.aqa.logging.DefaultListener;
import com.beton.aqa.pages.LivePage;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.beton.aqa.data.Global.USERNAME;

@Listeners(DefaultListener.class)
public class LoginTest extends BaseTest {

    @Test
    @Description("Log in")
    public void loginTest() {
        LivePage livePage = new LivePage();
        livePage.login();
        verifyGreeting(livePage);
    }

    private void verifyGreeting(LivePage livePage) {
        softVerify.verifyEquals(livePage.getGreetingText(),
                                String.format("Hello, %s", USERNAME), "Greeting message is correct");
    }
}
