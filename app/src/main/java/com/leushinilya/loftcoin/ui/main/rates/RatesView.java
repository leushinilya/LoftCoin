package com.leushinilya.loftcoin.ui.main.rates;

import com.leushinilya.loftcoin.data.Coin;

import java.util.List;

public interface RatesView {

    void showCoins(List<Coin> coinList);

    void showError(String error);
}
