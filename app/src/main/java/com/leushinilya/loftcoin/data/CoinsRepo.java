package com.leushinilya.loftcoin.data;

import androidx.annotation.WorkerThread;

import java.io.IOException;
import java.util.List;

public interface CoinsRepo {

    @WorkerThread
    List<Coin> listings(String currency) throws IOException;
}
