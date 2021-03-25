package com.gmail.pkotionov.components;

import com.gmail.pkotionov.data.CountryCurrency;
import com.gmail.pkotionov.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

import static com.gmail.pkotionov.utils.TimeOuts.PAGE_LOAD_TIMEOUT;

public class CurrencyTableComponent extends BaseComponent {

    private final By tableRowLocator = By.cssSelector("tr[data-ng-repeat='currency_to in currencyExchangeVM.supportedCurrencies']");
    private final By currencyRowNameLocator = By.cssSelector("td[data-ng-if='currencyExchangeVM.rates[currencyExchangeVM.PROVIDERS.OFFICIAL]']");

    private final String CURRENCY_USA = CountryCurrency.USA.getCurrency();

    public CurrencyTableComponent(WebElement element) {
        super(element);
    }

    public String getOfficialRateForUSA() {
        WebElement usaCurrencyRow = getUSACurrencyRow();
        CurrencyRowTableComponent rowTableComponent = new CurrencyRowTableComponent(usaCurrencyRow);
        return rowTableComponent.getOfficialRate();
    }

    public String getAlternativeRateForUSA() {
        WebElement usaCurrencyRow = getUSACurrencyRow();
        CurrencyRowTableComponent rowTableComponent = new CurrencyRowTableComponent(usaCurrencyRow);
        return rowTableComponent.getAlternativeRate();
    }

    public String getSwedBankUSAAmount() {
        WebElement usaCurrencyRow = getUSACurrencyRow();
        CurrencyRowTableComponent rowTableComponent = new CurrencyRowTableComponent(usaCurrencyRow);
        return rowTableComponent.getSwedBankAmount();
    }

    public String getAlternativeUSAAmount() {
        WebElement usaCurrencyRow = getUSACurrencyRow();
        CurrencyRowTableComponent rowTableComponent = new CurrencyRowTableComponent(usaCurrencyRow);
        return rowTableComponent.getAlternativeAmount();
    }

    public String getSwedBankUSAAmountLoss() {
        WebElement usaCurrencyRow = getUSACurrencyRow();
        CurrencyRowTableComponent rowTableComponent = new CurrencyRowTableComponent(usaCurrencyRow);
        return rowTableComponent.getSwedBankAmountLoss();
    }

    private WebElement getUSACurrencyRow() {
        WaitHelper.waitForElementPresence(getComponentElement(), tableRowLocator, PAGE_LOAD_TIMEOUT);

        List<WebElement> rows = getComponentElement().findElements(tableRowLocator);
        Optional<WebElement> usaRow = rows.stream()
                .filter(row -> row.findElement(currencyRowNameLocator).getText().contains(CURRENCY_USA))
                .findFirst();

        if (!usaRow.isPresent()) {
            throw new RuntimeException("Row with currency " + CURRENCY_USA + " not found");
        }
        return usaRow.get();
    }
}
