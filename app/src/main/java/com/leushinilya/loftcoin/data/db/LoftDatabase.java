package com.leushinilya.loftcoin.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = RoomCoin.class, version = 1)
public abstract class LoftDatabase extends RoomDatabase {

    public abstract CoinsDAO coins();

}
