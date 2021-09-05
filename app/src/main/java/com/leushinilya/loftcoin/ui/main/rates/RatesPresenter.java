package com.leushinilya.loftcoin.ui.main.rates;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.leushinilya.loftcoin.data.CmcCoinsRepo;
import com.leushinilya.loftcoin.data.Coin;
import com.leushinilya.loftcoin.data.CoinsRepo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RatesPresenter {

    private final CoinsRepo coinsRepo;
    private List<Coin> coins = Collections.emptyList();
    private final ExecutorService executor;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private RatesView ratesView;

    public RatesPresenter() {
        this.coinsRepo = new CmcCoinsRepo();
        this.executor = Executors.newSingleThreadExecutor();
        refresh();
    }

    private void refresh() {
        executor.submit(() -> {
            try {
                coins = coinsRepo.listings("USD");
                handler.post(() -> onSuccess(coins));
            } catch (IOException e) {
                handler.post(() -> onError(e));
            }
        });
    }

    void attach(@NonNull RatesView ratesView) {
        this.ratesView = ratesView;
        if (!coins.isEmpty()) {
            ratesView.showCoins(coins);
        }
    }

    void detach(@NonNull RatesView ratesView) {
    }

    private void onSuccess(List<Coin> coins) {
        this.coins = coins;
        if (ratesView != null) {
            ratesView.showCoins(coins);
        }
    }

    private void onError(IOException e) {
    }

}
