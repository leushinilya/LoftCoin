package com.leushinilya.loftcoin.data;

import com.google.gson.annotations.SerializedName;

public class Coin {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

}
