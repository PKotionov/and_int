package com.gmail.pkotionov.components;

import com.gmail.pkotionov.data.CountryCurrency;
import com.gmail.pkotionov.utils.ScrollHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class CountryDropdownComponent extends BaseComponent {

    @FindBy(css = "#countries-dropdown")
    private WebElement selectedCountryField;

    @FindBy(css = "[aria-labelledby='countries-dropdown']")
    private WebElement dropdownMenuCountryElement;

    private final By COUNTRY_LOCATOR = By.xpath(".//li/a");

    public CountryDropdownComponent(WebElement element) {
        super(element);
    }

    public void selectCountry(CountryCurrency country){
        ScrollHelper.scrollToBottom();
        selectedCountryField.click();

        List<WebElement> countries = dropdownMenuCountryElement.findElements(COUNTRY_LOCATOR);
        Optional<WebElement> countryOptional = countries.stream()
                .filter(countryElement -> countryElement.getText().equals(country.getCountryName()))
                .findFirst();

        if(!countryOptional.isPresent()){
            throw new WebDriverException("Country '" + country + "' is not found in list");
        }

        countryOptional.get().click();
    }
}
