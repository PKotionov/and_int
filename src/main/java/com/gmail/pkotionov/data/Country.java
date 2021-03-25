package com.gmail.pkotionov.data;

public enum Country {

    RUSSIA("Russia", "RUB"),
    SPAIN("Spain", "EUR"),
    USA("USA", "US Dollar");

    private String countryName;
    private String currency;

    Country(String countryName, String currency) {
        this.countryName = countryName;
        this.currency = currency;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrency() {
        return currency;
    }
}
