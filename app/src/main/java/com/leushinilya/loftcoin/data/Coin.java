package com.leushinilya.loftcoin.data;

import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.Map;

public class Coin {

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice(){
        Iterator<Quote> iterator = quote.values().iterator();
        if(iterator.hasNext())
        return iterator.next().price;
        else return 0;
    }

    public double getPercent(){
        Iterator<Quote> iterator = quote.values().iterator();
        if(iterator.hasNext())
            return iterator.next().percent;
        else return 0;
    }

}
