package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.components.CountryDropdownComponent;
import com.gmail.pkotionov.data.Country;
import com.gmail.pkotionov.pages.CurrencyCalculatorPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateCurrencyAccordingCountryTest extends BaseTest {

    @Test
    public void verifySwitchingCurrencyByCountry() {
        SoftAssert softAssert = new SoftAssert();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        CountryDropdownComponent countryDropdownComponent = calculatorPage.openCountryDropdown();
        countryDropdownComponent.selectCountry(Country.SPAIN);

        softAssert.assertEquals(calculatorPage.getSellCurrency(), Country.SPAIN.getCurrency(),
                "Sell currency is not match to expected");

        String usaOfficialRateForFirstCountry = calculatorPage.getOfficialRateForUSA();
        String usaAlternativeRateForFirstCountry = calculatorPage.getAlternativeRateForUSA();

        countryDropdownComponent = calculatorPage.openCountryDropdown();
        countryDropdownComponent.selectCountry(Country.RUSSIA);

        softAssert.assertEquals(calculatorPage.getSellCurrency(), Country.RUSSIA.getCurrency(),
                "Sell currency is not match to expected");
        softAssert.assertNotEquals(calculatorPage.getOfficialRateForUSA(), usaOfficialRateForFirstCountry, "Official Rate for USA Dollar is changed");
        softAssert.assertNotEquals(calculatorPage.getAlternativeRateForUSA(), usaAlternativeRateForFirstCountry, "Alternative Rate for USA Dollar is changed");

        softAssert.assertAll();
    }
}
