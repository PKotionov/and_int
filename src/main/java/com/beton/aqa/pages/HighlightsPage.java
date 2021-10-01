package com.beton.aqa.pages;

import com.beton.aqa.components.LoginFormComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HighlightsPage extends BasePage {

    @FindBy(xpath = "//span[text()='Log in']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='Accept']/following-sibling::span")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//div[contains(@class, 'loginForm')]")
    private WebElement loginFormElement;

    public HighlightsPage() {
        super();
        acceptCookies();
    }

    private void acceptCookies() {
        if (acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    @Step("Login to the system")
    public void login() {
        loginButton.click();
        LoginFormComponent loginFormComponent = new LoginFormComponent(loginFormElement);
        loginFormComponent.login();
    }
}