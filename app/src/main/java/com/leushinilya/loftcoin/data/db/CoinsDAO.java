package com.leushinilya.loftcoin.data.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import java.util.List;

@Dao
public interface CoinsDAO {

    @Query("SELECT * FROM RoomCoin")
    List<RoomCoin> fetchAll();

    @WorkerThread
    @Query("SELECT COUNT(id) FROM RoomCoin")
    int coinsCount();

    @Insert(onConflict = REPLACE)
    void insert(List<RoomCoin> coins);

}
