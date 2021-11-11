package com.leushinilya.loftcoin.data.rates;

public interface Coin {

    int getId();
    String getName();
    String getSymbol();
    double getPrice();
    double getPercent();}
