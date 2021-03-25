package com.gmail.pkotionov.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CurrencyDropdownComponent extends BaseComponent {

    private final String CURRENCY_LOCATOR_PATTERN = "//span[@data-ng-bind='currency' and text()='%s']";

    public CurrencyDropdownComponent(WebElement element) {
        super(element);
    }

    public void selectCurrency(String currency) {
        By currencyLocator = getCurrencyLocator(currency);
        WebElement currencyElement = getComponentElement().findElement(currencyLocator);
        currencyElement.click();
    }

    private By getCurrencyLocator(String currency) {
        return By.xpath(String.format(CURRENCY_LOCATOR_PATTERN, currency));
    }
}
