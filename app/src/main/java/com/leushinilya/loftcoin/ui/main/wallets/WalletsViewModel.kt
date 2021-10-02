package com.leushinilya.loftcoin.ui.main.wallets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leushinilya.loftcoin.data.rates.Coin
import com.leushinilya.loftcoin.data.rates.db.RoomCoin
import com.leushinilya.loftcoin.data.wallets.Wallet
import com.leushinilya.loftcoin.data.wallets.WalletsRepo
import java.util.*
import javax.inject.Inject

class WalletsViewModel: ViewModel() {

    var wallets = MutableLiveData<List<Wallet>>(Collections.emptyList())

    @Inject
    lateinit var walletsRepo: WalletsRepo

    fun refreshWallets(){

        val list = Collections.emptyList<Wallet>()
        list.add(Wallet("123", RoomCoin(1, "Bitcoin", "BTC", 1000.0, 10.0), 1.23))
        wallets.postValue(list)
    }
}