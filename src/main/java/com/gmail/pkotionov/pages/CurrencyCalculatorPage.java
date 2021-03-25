package com.gmail.pkotionov.pages;

import com.gmail.pkotionov.components.CountryDropdownComponent;
import com.gmail.pkotionov.components.CurrencyDropdownComponent;
import com.gmail.pkotionov.components.CurrencyTableComponent;
import com.gmail.pkotionov.driver.DriverProvider;
import com.gmail.pkotionov.utils.ScrollHelper;
import com.gmail.pkotionov.utils.WaitHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;

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

    public void clickToSellAmount() {
        logger.info("Click to sell amount field");
        ScrollHelper.scrollToElement(sellField);
        sellField.click();
    }

    public String getSellAmount() {
        return sellField.getAttribute("value");
    }

    public void setBuyAmount(String amount) {
        logger.info("Set buy amount: " + amount);
        ScrollHelper.scrollToElement(buyField);
        WaitHelper.waitForElementStayInvisible(pageLoaderElement, 10);
        buyField.clear();
        buyField.sendKeys(amount);
    }

    public void clickToBuyAmount() {
        logger.info("Click to buy amount");
        ScrollHelper.scrollToElement(buyField);
        buyField.click();
    }

    public String getBuyAmount() {
        return buyField.getAttribute("value");
    }

    public CountryDropdownComponent openCountryDropdown() {
        logger.info("Open Country dropdown");
        ScrollHelper.scrollToElement(selectCountryIconElement);
        selectCountryIconElement.click();
        return new CountryDropdownComponent(selectCountryPopup);
    }

    public String getSellCurrency() {
        ScrollHelper.scrollToElement(sellCurrencyTextElement);
        WaitHelper.waitForElementHasText(sellCurrencyTextElement, 10);
        return sellCurrencyTextElement.getText();
    }

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
        WaitHelper.waitForElementPresence(pageLoaderElement, 1);
        return WaitHelper.waitForElementStayInvisible(pageLoaderElement, 10);
    }
}
