package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.components.CountryDropdownComponent;
import com.gmail.pkotionov.data.CountryCurrency;
import com.gmail.pkotionov.logging.DefaultListener;
import com.gmail.pkotionov.logging.SoftVerify;
import com.gmail.pkotionov.pages.CurrencyCalculatorPage;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(DefaultListener.class)
public class UpdateCurrencyAccordingCountryTest extends BaseTest {

    @Test
    @Description("Rates and Currency updates after country changed")
    public void verifySwitchingCurrencyByCountry() {
        SoftVerify softVerify = new SoftVerify();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        CountryDropdownComponent countryDropdownComponent = calculatorPage.openCountryDropdown();
        countryDropdownComponent.selectCountry(CountryCurrency.SPAIN);

        softVerify.assertEquals(calculatorPage.getSellCurrency(), CountryCurrency.SPAIN.getCurrency(),
                "Sell currency matches to expected");

        String usaOfficialRateForFirstCountry = calculatorPage.getOfficialRateForUSA();
        String usaAlternativeRateForFirstCountry = calculatorPage.getAlternativeRateForUSA();

        countryDropdownComponent = calculatorPage.openCountryDropdown();
        countryDropdownComponent.selectCountry(CountryCurrency.RUSSIA);

        softVerify.assertEquals(calculatorPage.getSellCurrency(), CountryCurrency.RUSSIA.getCurrency(),
                "Sell currency matches to expected");
        softVerify.assertNotEquals(calculatorPage.getOfficialRateForUSA(), usaOfficialRateForFirstCountry,
                "Official Rate for USA Dollar is changed");
        softVerify.assertNotEquals(calculatorPage.getAlternativeRateForUSA(), usaAlternativeRateForFirstCountry,
                "Alternative Rate for USA Dollar is changed");

        softVerify.assertAll();
    }
}
