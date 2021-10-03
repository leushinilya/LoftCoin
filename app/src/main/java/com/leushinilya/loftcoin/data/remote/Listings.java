package com.leushinilya.loftcoin.data.remote;

import com.google.gson.annotations.SerializedName;
import com.leushinilya.loftcoin.data.remote.CmcCoin;

import java.util.List;

public class Listings {

    @SerializedName("data")
    public List<CmcCoin> cmcCoins;

}
