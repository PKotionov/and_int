package com.gmail.pkotionov.utils;

public class TimeOuts {

    public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertyHelper.getProperty("timeouts.page"));
    public static final int ELEMENT_TIMEOUT = Integer.parseInt(PropertyHelper.getProperty("timeouts.element"));
    public static final int MINIMAL_ELEMENT_TIMEOUT = Integer.parseInt(PropertyHelper.getProperty("timeouts.minimal"));
}
