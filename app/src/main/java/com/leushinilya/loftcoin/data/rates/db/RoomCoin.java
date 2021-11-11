package com.leushinilya.loftcoin.data.rates.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.leushinilya.loftcoin.data.rates.Coin;

@Entity
public class RoomCoin implements Coin {

    public RoomCoin(int id, String name, String symbol, double price, double percent) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.percent = percent;
    }

    @PrimaryKey
    private int id;

    private String name;
    private String symbol;
    double price;
    double percent;


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
    public double getPrice() {
        return price;
    }

    @Override
    public double getPercent() {
        return percent;
    }
}
