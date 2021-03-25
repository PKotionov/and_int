package com.gmail.pkotionov.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CurrencyRowTableComponent extends BaseComponent {

    @FindBy(css = "td[data-title='Official rate'] span.ng-binding")
    private WebElement officialRateElement;

    @FindBy(css = "td[data-ng-repeat-start='provider in currencyExchangeVM.supportedProviders']")
    private WebElement alternativeRateElement;

    @FindBy(xpath = ".//td[@data-title='Swedbank amount']//span[@class='ng-binding']")
    private WebElement swedBankAmountElement;

    @FindBy(xpath = ".//td[@data-title='Swedbank amount']//span[contains(@class, 'other-bank-loss')]")
    private WebElement swedBankAmountLossElement;

    @FindBy(xpath = ".//td[@data-title='Paysera rate']//span[@class='ng-binding']")
    private WebElement alternativeBankAmountElement;

    public CurrencyRowTableComponent(WebElement element) {
        super(element);
    }

    public String getOfficialRate() {
        return officialRateElement.getText();
    }

    public String getAlternativeRate() {
        return alternativeRateElement.getText();
    }

    public String getSwedBankAmount() {
        return swedBankAmountElement.getText();
    }

    public String getAlternativeAmount() {
        return alternativeBankAmountElement.getText();
    }

    public String getSwedBankAmountLoss() {
        return swedBankAmountLossElement.getText();
    }
}
