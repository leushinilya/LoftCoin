package com.leushinilya.loftcoin.data.rates.remote;

import com.google.gson.annotations.SerializedName;
import com.leushinilya.loftcoin.data.rates.Coin;

import java.util.Iterator;
import java.util.Map;

public class CmcCoin implements Coin {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("quote")
    private Map<String, Quote> quote;

    static class Quote{

        @SerializedName("price")
        double price;

        @SerializedName("percent_change_24h")
        double percent;

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getPrice(){
        Iterator<Quote> iterator = quote.values().iterator();
        if(iterator.hasNext())
        return iterator.next().price;
        else return 0;
    }

    @Override
    public double getPercent(){
        Iterator<Quote> iterator = quote.values().iterator();
        if(iterator.hasNext())
            return iterator.next().percent;
        else return 0;
    }

}
