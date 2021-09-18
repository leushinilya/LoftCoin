package com.leushinilya.loftcoin.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listings {

    @SerializedName("data")
    public List<Coin> coins;

}
