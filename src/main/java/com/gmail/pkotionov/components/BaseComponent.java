package com.gmail.pkotionov.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class BaseComponent {

    private WebElement componentElement;

    public BaseComponent(WebElement element){
        DefaultElementLocatorFactory parentContext = new DefaultElementLocatorFactory(element);
        PageFactory.initElements(parentContext, this);
        this.componentElement = element;
    }

    protected WebElement getComponentElement(){
        return componentElement;
    }
}
