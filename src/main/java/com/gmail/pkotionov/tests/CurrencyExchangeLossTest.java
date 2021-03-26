package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.data.CountryCurrency;
import com.gmail.pkotionov.logging.DefaultListener;
import com.gmail.pkotionov.logging.SoftVerify;
import com.gmail.pkotionov.pages.CurrencyCalculatorPage;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@Listeners(DefaultListener.class)
public class CurrencyExchangeLossTest extends BaseTest {

    private static final String VALUE = "5" + RandomStringUtils.randomNumeric(2);

    @Test
    @Description("Loss amount is displayed when alternative bank provides better rate")
    public void verifyCurrencyLoss() {
        SoftVerify softVerify = new SoftVerify();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.selectSellCurrency(CountryCurrency.SPAIN.getCurrency());

        calculatorPage.interSellAmount(VALUE);

        final BigDecimal swedBankAmount = calculatorPage.getSwedBankAmount();
        final BigDecimal alternativeAmount = calculatorPage.getAlternativeAmount();
        final BigDecimal swedBankAmountLoss = calculatorPage.getSwedBankAmountLoss();

        softVerify.assertEquals(swedBankAmount.subtract(alternativeAmount), swedBankAmountLoss,
                "Amount loss for Swed Bank is correct");

        softVerify.assertAll();
    }
}
