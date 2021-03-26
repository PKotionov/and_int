package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.logging.DefaultListener;
import com.gmail.pkotionov.logging.SoftVerify;
import com.gmail.pkotionov.pages.CurrencyCalculatorPage;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.util.Strings;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(DefaultListener.class)
public class AutoEraseFieldTest extends BaseTest {

    @DataProvider(name = "currencyValueDataProvider")
    public Object[][] manageDataProvider() {
        return new Object[][]{
                {"1"},
                {"0"},
                {RandomStringUtils.randomNumeric(3)},
                {"-" + RandomStringUtils.randomNumeric(3)},
                {" "},
                {"0.0001"},
                {RandomStringUtils.randomAlphabetic(1)},
                {RandomStringUtils.randomAscii(10)},
                {RandomStringUtils.randomAlphanumeric(500)},
        };
    }

    @Test(dataProvider = "currencyValueDataProvider")
    @Description("'Sell' amount box is being emptied when fills 'Buy' amount")
    public void verifySellAmountAutoErasing(String value) {
        SoftVerify softVerify = new SoftVerify();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.setSellAmount(value);
        calculatorPage.setBuyAmount(value);

        softVerify.assertEquals(calculatorPage.getSellAmount(), Strings.EMPTY, "Sell value is emptied");

        softVerify.assertAll();
    }

    @Test(dataProvider = "currencyValueDataProvider")
    @Description("'Buy' amount box is being emptied when fills 'Sell' amount")
    public void verifyBuyAmountAutoErasing(String value) {
        SoftVerify softVerify = new SoftVerify();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.setBuyAmount(value);
        calculatorPage.setSellAmount(value);

        softVerify.assertEquals(calculatorPage.getBuyAmount(), Strings.EMPTY, "Buy value is emptied");

        softVerify.assertAll();
    }

    @Test
    @Description("'Sell' amount box is not being emptied when clicks on 'Buy' amount")
    public void verifySellAmountIsNotErasedAfterClickToBuyAmount() {
        SoftVerify softVerify = new SoftVerify();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();

        String value = RandomStringUtils.randomAlphanumeric(3);
        calculatorPage.setSellAmount(value);
        calculatorPage.clickOnBuyAmount();

        softVerify.assertEquals(calculatorPage.getSellAmount(), value, "Sell value is not emptied");

        softVerify.assertAll();
    }

    @Test
    @Description("'Buy' amount box is not being emptied when clicks on 'Sell' amount")
    public void verifyBuyAmountIsNotErasedAfterClickToSellAmount() {
        SoftVerify softVerify = new SoftVerify();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();

        String value = RandomStringUtils.randomAlphanumeric(3);
        calculatorPage.setBuyAmount(value);
        calculatorPage.clickOnSellAmount();

        softVerify.assertEquals(calculatorPage.getBuyAmount(), value, "Buy value is not emptied");

        softVerify.assertAll();
    }
}
