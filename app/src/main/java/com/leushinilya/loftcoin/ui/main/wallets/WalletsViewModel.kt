package com.leushinilya.loftcoin.ui.main.wallets

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leushinilya.loftcoin.data.wallets.Wallet
import com.leushinilya.loftcoin.data.wallets.WalletsRepo
import javax.inject.Inject

class WalletsViewModel : ViewModel() {

    var wallets = MutableLiveData<ArrayList<Wallet>>()

    @Inject
    lateinit var walletsRepo: WalletsRepo

    fun refreshWallets() {
        walletsRepo.wallets(wallets)
    }
}