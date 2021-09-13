package com.leushinilya.loftcoin.ui.main.rates

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.MutableLiveData
import com.leushinilya.loftcoin.data.Coin
import com.leushinilya.loftcoin.data.CmcAPI
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import com.leushinilya.loftcoin.data.Listings

class RatesViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    @JvmField
    var liveDataCoins = MutableLiveData<List<Coin>>(emptyList())
    @JvmField
    var isRefreshing = MutableLiveData(true)
    @JvmField
    var currency = MutableLiveData("USD")
    var message = MutableLiveData("")
    var sorting = MutableLiveData(-1)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getRemoteCoins(cmcAPI: CmcAPI, currency: String?) {
        val disposable = cmcAPI.listings(currency)
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