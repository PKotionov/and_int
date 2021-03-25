package com.gmail.pkotionov.tests;

import com.gmail.pkotionov.pages.CurrencyCalculatorPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.util.Strings;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    public void verifySellAmountAutoErasing(String value) {
        SoftAssert softAssert = new SoftAssert();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.setSellAmount(value);
        calculatorPage.setBuyAmount(value);

        softAssert.assertEquals(calculatorPage.getSellAmount(), Strings.EMPTY, "Sell value is not empty");

        softAssert.assertAll();
    }

    @Test(dataProvider = "currencyValueDataProvider")
    public void verifyBuyAmountAutoErasing(String value) {
        SoftAssert softAssert = new SoftAssert();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.setBuyAmount(value);
        calculatorPage.setSellAmount(value);

        softAssert.assertEquals(calculatorPage.getBuyAmount(), Strings.EMPTY, "Buy value is not empty");

        softAssert.assertAll();
    }

    @Test
    public void verifySellAmountIsNotErasedAfterClickToBuyAmount() {
        SoftAssert softAssert = new SoftAssert();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();

        String value = RandomStringUtils.randomAlphanumeric(3);
        calculatorPage.setSellAmount(value);
        calculatorPage.clickToBuyAmount();

        softAssert.assertEquals(calculatorPage.getSellAmount(), value, "Sell value is erased");

        softAssert.assertAll();
    }

    @Test
    public void verifyBuyAmountIsNotErasedAfterClickToSellAmount() {
        SoftAssert softAssert = new SoftAssert();

        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();

        String value = RandomStringUtils.randomAlphanumeric(3);
        calculatorPage.setBuyAmount(value);
        calculatorPage.clickToSellAmount();

        softAssert.assertEquals(calculatorPage.getBuyAmount(), value, "Buy value is erased");

        softAssert.assertAll();
    }
}
