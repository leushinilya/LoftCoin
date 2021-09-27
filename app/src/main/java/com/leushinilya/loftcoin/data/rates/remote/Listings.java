package com.leushinilya.loftcoin.data.rates.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listings {

    @SerializedName("data")
    public List<CmcCoin> cmcCoins;

}
