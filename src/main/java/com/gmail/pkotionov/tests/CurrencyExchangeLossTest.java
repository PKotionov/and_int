package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.data.Country;
import com.gmail.pkotionov.pages.CurrencyCalculatorPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;

public class CurrencyExchangeLossTest extends BaseTest {

    private static final String VALUE = "5" + RandomStringUtils.randomNumeric(2);

    @Test
    public void verifyCurrencyLoss() {
        SoftAssert softAssert = new SoftAssert();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.selectSellCurrency(Country.SPAIN.getCurrency());

        calculatorPage.interSellAmount(VALUE);

        final BigDecimal swedBankAmount = calculatorPage.getSwedBankAmount();
        final BigDecimal alternativeAmount = calculatorPage.getAlternativeAmount();
        final BigDecimal swedBankAmountLoss = calculatorPage.getSwedBankAmountLoss();

        softAssert.assertEquals(swedBankAmount.subtract(alternativeAmount), swedBankAmountLoss, "Amount loss for Swed Bank is incorrect");

        softAssert.assertAll();
    }
}
