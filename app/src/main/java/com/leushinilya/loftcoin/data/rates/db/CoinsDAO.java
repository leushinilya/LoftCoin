package com.leushinilya.loftcoin.data.rates.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoinsDAO {

    @Query("SELECT * FROM RoomCoin")
    List<RoomCoin> fetchAll();

    @Query("SELECT * FROM RoomCoin WHERE id=:id")
    RoomCoin fetchOne(long id);

    @WorkerThread
    @Query("SELECT COUNT(id) FROM RoomCoin")
    int coinsCount();

    @Insert(onConflict = REPLACE)
    void insert(List<RoomCoin> coins);

}
