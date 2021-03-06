package com.gmail.pkotionov.pages;

import com.gmail.pkotionov.components.CountryDropdownComponent;
import com.gmail.pkotionov.components.CurrencyDropdownComponent;
import com.gmail.pkotionov.components.CurrencyTableComponent;
import com.gmail.pkotionov.utils.ScrollHelper;
import com.gmail.pkotionov.utils.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

import static com.gmail.pkotionov.utils.TimeOuts.MINIMAL_ELEMENT_TIMEOUT;
import static com.gmail.pkotionov.utils.TimeOuts.PAGE_LOAD_TIMEOUT;

public class CurrencyCalculatorPage extends BasePage {

    @FindBy(css = "[data-ng-model='currencyExchangeVM.filter.from_amount']")
    private WebElement sellField;

    @FindBy(css = "[data-ng-model='currencyExchangeVM.filter.from'] span[data-ng-bind='$select.selected']")
    private WebElement sellCurrencyTextElement;

    @FindBy(css = "[data-ng-model='currencyExchangeVM.filter.to_amount']")
    private WebElement buyField;

    @FindBy(css = "[data-ng-model='currencyExchangeVM.filter.to'] span[data-ng-bind='$select.selected']")
    private WebElement buyCurrencyTextElement;

    @FindBy(css = ".js-localization-popover")
    private WebElement selectCountryIconElement;

    @FindBy(css = "div.popover-content")
    private WebElement selectCountryPopup;

    @FindBy(xpath = "td[data-ng-if='currencyExchangeVM.rates[currencyExchangeVM.PROVIDERS.OFFICIAL]']")
    private WebElement officialRateUSAElement;

    @FindBy(css = "table.transformable-table")
    private WebElement currencyTableElement;

    @FindBy(xpath = "//label[text()='Sell']//following::ul[contains(@class, 'ui-select-dropdown')]")
    private WebElement sellCurrencyDropdown;

    @FindBy(xpath = "//label[text()='Buy']//following::ul[contains(@class, 'ui-select-dropdown')]")
    private WebElement buyCurrencyDropdown;

    @FindBy(css = "div[data-ng-show='currencyExchangeVM.loading']")
    private WebElement pageLoaderElement;

    @Step("Set sell amount: {amount}")
    public void setSellAmount(String amount) {
        logger.info("Set sell amount: " + amount);
        ScrollHelper.scrollToElement(sellField);
        sellField.clear();
        sellField.sendKeys(amount);
    }

    public void interSellAmount(String amount) {
        setSellAmount(amount);
        logger.info("Press ENTER");
        sellField.sendKeys(Keys.ENTER);
        waitForPageLoading();
    }

    @Step("Click on Sell amount field")
    public void clickOnSellAmount() {
        logger.info("Click to sell amount field");
        ScrollHelper.scrollToElement(sellField);
        sellField.click();
    }

    public String getSellAmount() {
        return sellField.getAttribute("value");
    }

    @Step("Set buy amount: {amount}")
    public void setBuyAmount(String amount) {
        logger.info("Set buy amount: " + amount);
        ScrollHelper.scrollToElement(buyField);
        WaitHelper.waitForElementStayInvisible(pageLoaderElement, PAGE_LOAD_TIMEOUT);
        buyField.clear();
        buyField.sendKeys(amount);
    }

    @Step("Click on Buy amount field")
    public void clickOnBuyAmount() {
        logger.info("Click to buy amount");
        ScrollHelper.scrollToElement(buyField);
        buyField.click();
    }

    public String getBuyAmount() {
        return buyField.getAttribute("value");
    }

    @Step("Open Country dropdown")
    public CountryDropdownComponent openCountryDropdown() {
        logger.info("Open Country dropdown");
        ScrollHelper.scrollToElement(selectCountryIconElement);
        selectCountryIconElement.click();
        return new CountryDropdownComponent(selectCountryPopup);
    }

    public String getSellCurrency() {
        ScrollHelper.scrollToElement(sellCurrencyTextElement);
        WaitHelper.waitForElementHasText(sellCurrencyTextElement, PAGE_LOAD_TIMEOUT);
        return sellCurrencyTextElement.getText();
    }

    @Step("Select Sell currency '{currency}'")
    public void selectSellCurrency(String currency) {
        logger.info("Select Sell currency: " + currency);
        if (!getSellCurrency().equals(currency)) {
            sellCurrencyTextElement.click();
            CurrencyDropdownComponent currencyDropdownComponent = new CurrencyDropdownComponent(sellCurrencyDropdown);
            currencyDropdownComponent.selectCurrency(currency);
        }
    }

    public String getOfficialRateForUSA() {
        logger.info("Get Official Rate For USA currency");
        CurrencyTableComponent currencyTableComponent = new CurrencyTableComponent(currencyTableElement);
        return currencyTableComponent.getOfficialRateForUSA();
    }

    public String getAlternativeRateForUSA() {
        logger.info("Get Alternative Rate For USA currency");
        CurrencyTableComponent currencyTableComponent = new CurrencyTableComponent(currencyTableElement);
        return currencyTableComponent.getAlternativeRateForUSA();
    }

    public BigDecimal getSwedBankAmount() {
        logger.info("Get SwedBank amount");

        CurrencyTableComponent currencyTableComponent = new CurrencyTableComponent(currencyTableElement);
        String swedBankUSAAmount = currencyTableComponent.getSwedBankUSAAmount();
        logger.info("Got SwedBank amount: " + swedBankUSAAmount);
        return new BigDecimal(swedBankUSAAmount);
    }

    public BigDecimal getAlternativeAmount() {
        logger.info("Get Alternative Amount For USA currency");
        CurrencyTableComponent currencyTableComponent = new CurrencyTableComponent(currencyTableElement);
        String alternativeAmount = currencyTableComponent.getAlternativeUSAAmount();
        return new BigDecimal(alternativeAmount);
    }

    public BigDecimal getSwedBankAmountLoss() {
        logger.info("Get SwedBank Amount Loss for USA currency");
        CurrencyTableComponent currencyTableComponent = new CurrencyTableComponent(currencyTableElement);
        String swedBankAmountLoss = currencyTableComponent.getSwedBankUSAAmountLoss();

        //remove brackets
        swedBankAmountLoss = swedBankAmountLoss.substring(1, swedBankAmountLoss.length() - 1);

        logger.info("Got SwedBank amount loss: " + swedBankAmountLoss);
        return new BigDecimal(swedBankAmountLoss);
    }

    private boolean waitForPageLoading() {
        logger.debug("Waiting page is loading");
        WaitHelper.waitForElementPresence(pageLoaderElement, MINIMAL_ELEMENT_TIMEOUT);
        return WaitHelper.waitForElementStayInvisible(pageLoaderElement, PAGE_LOAD_TIMEOUT);
    }
}
