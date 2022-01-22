package com.beton.aqa.pages;

import com.beton.aqa.annotates.PageName;
import com.beton.aqa.annotates.Required;
import com.beton.aqa.components.LoginFormComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageName("Live")
public class LivePage extends BasePage {

    @Required()
    @FindBy(css = "button.Header-loginButton-29")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='Accept']/following-sibling::span")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//div[contains(@class, 'loginForm')]")
    private WebElement loginFormElement;

    @FindBy(xpath = "//span[contains(@class, 'UserDropdownMenu-helloLable')]")
    private WebElement greetingMessageElement;

    public LivePage() {
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

    public String getGreetingText() {
        return greetingMessageElement.getText();
    }
}