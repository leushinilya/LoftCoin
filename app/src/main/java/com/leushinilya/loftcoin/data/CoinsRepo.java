package com.leushinilya.loftcoin.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.leushinilya.loftcoin.data.db.CoinsDAO;
import com.leushinilya.loftcoin.data.db.LoftDatabase;
import com.leushinilya.loftcoin.data.db.RoomCoin;
import com.leushinilya.loftcoin.data.remote.CmcAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

//@Singleton
public class CoinsRepo {

//    @Inject
    public CoinsRepo(LoftDatabase db, ExecutorService executor) {
        this.db = db;
        this.executor = executor;
    }

    CoinsDAO coinsDAO;
    LoftDatabase db;
    ExecutorService executor;

    public LiveData<List<RoomCoin>> listings(String currency, boolean forceUpdate) {
        return Transformations.map(db.coins().fetchAll(), ArrayList::new);
    }

    public void saveCoinsIntoDB(List<Coin> coins) {
        List<RoomCoin> roomCoins = Collections.emptyList();
        for (Coin coin : coins) {
            roomCoins.add(new RoomCoin(
                    coin.getId(),
                    coin.getName(),
                    coin.getSymbol(),
                    coin.getPrice(),
                    coin.getPercent()));
        }
        db.coins().insert(roomCoins);
    }

}
