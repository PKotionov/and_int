package com.beton.aqa.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.beton.aqa.data.Global.PASSWORD;
import static com.beton.aqa.data.Global.USERNAME;

public class LoginFormComponent extends BaseComponent {

    @FindBy(css = "input[name='username']")
    private WebElement usernameField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//span[text()='Login' and @class='MuiButton-label']")
    private WebElement submitButton;

    public LoginFormComponent(WebElement element) {
        super(element);
    }

    public void login() {
        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        submitButton.click();
    }
}