package com.beton.aqa.pages;

import com.beton.aqa.annotates.PageName;
import com.beton.aqa.annotates.Required;
import com.beton.aqa.driver.DriverProvider;
import com.beton.aqa.logging.Verify;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
        verifyPageIsOpened();
    }

    private void verifyPageIsOpened() {
        getRequiredElements().forEach(element -> {
            try {
                Verify.assertTrue(element.isDisplayed(),
                                  String.format("page '%s' is opened", getPageName()));
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException(String.format("\nPage '%s' is not opened. Required element is not displayed. \n%s",
                                                               getPageName(), e.getMessage()));
            }
        });
    }

    private String getPageName() {
        if (getClass().isAnnotationPresent(PageName.class)) {
            return getClass().getAnnotation(PageName.class)
                             .value();
        }
        return getClass().getName();
    }

    private Object getFieldValue(Field field, Object owner) {
        field.setAccessible(Boolean.TRUE);
        try {
            return field.get(owner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Exception during getting value from field " + field.getName());
        }
    }

    private WebElement castToWebElement(Object object) {
        if (object instanceof WebElement) {
            return (WebElement) object;
        } else {
            return null;
        }
    }

    private List<WebElement> getRequiredElements() {
        return Arrays.stream(getClass().getDeclaredFields())
                     .filter(field -> field.isAnnotationPresent(Required.class))
                     .map(field -> getFieldValue(field, this))
                     .map(this::castToWebElement)
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());
    }
}
