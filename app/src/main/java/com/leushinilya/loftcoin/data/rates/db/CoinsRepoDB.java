package com.leushinilya.loftcoin.data.rates.db;

import com.leushinilya.loftcoin.data.rates.Coin;
import com.leushinilya.loftcoin.data.rates.remote.CmcAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CoinsRepoDB {

    CmcAPI cmcAPI;
    public LoftDatabase db;
    public ExecutorService executor;

    @Inject
    public CoinsRepoDB(CmcAPI cmcAPI, LoftDatabase db, ExecutorService executor) {
        this.cmcAPI = cmcAPI;
        this.db = db;
        this.executor = executor;
    }

    public void saveCoinsIntoDB(List<? extends Coin> coins) {
        executor.submit(()->{
            List<RoomCoin> roomCoins = new ArrayList<>(coins.size());
            for (Coin coin : coins) {
                roomCoins.add(new RoomCoin(
                        coin.getId(),
                        coin.getName(),
                        coin.getSymbol(),
                        coin.getPrice(),
                        coin.getPercent()));
            }
            db.coins().insert(roomCoins);
        });

    }

}
