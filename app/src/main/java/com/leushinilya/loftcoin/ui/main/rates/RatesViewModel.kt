package com.leushinilya.loftcoin.ui.main.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leushinilya.loftcoin.data.CmcAPI
import com.leushinilya.loftcoin.data.CmcRepo
import com.leushinilya.loftcoin.data.Coin
import com.leushinilya.loftcoin.data.Listings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RatesViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val liveDataCoins = MutableLiveData<List<Coin>>(emptyList())
    val isRefreshing = MutableLiveData(true)
    val currency = MutableLiveData("USD")
    val message = MutableLiveData("")
    val sorting = MutableLiveData(-1)
    private val cmcRepo = CmcRepo()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getRemoteCoins(currency: String?) {

        val disposable = cmcRepo.cmcAPI.listings(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ remoteCoins: Listings ->
                    isRefreshing.postValue(true)
                    remoteCoins.coins.sortBy { sorting.value?.times(it.price) }
                    liveDataCoins.postValue(remoteCoins.coins)
                    isRefreshing.postValue(false)
                }) { throwable: Throwable -> message.postValue(throwable.localizedMessage) }
        compositeDisposable.add(disposable)

    }
}