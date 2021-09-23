package com.leushinilya.loftcoin.ui.main.rates

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leushinilya.loftcoin.data.Coin
import com.leushinilya.loftcoin.data.db.CoinsRepoDB
import com.leushinilya.loftcoin.data.remote.CmcAPI
import com.leushinilya.loftcoin.data.remote.Listings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var liveDataCoins = MutableLiveData<List<Coin>>(emptyList())
    val isRefreshing = MutableLiveData(true)
    val currency = MutableLiveData("USD")
    val sorting = MutableLiveData(-1)
    val isViewCreated = MutableLiveData<Boolean>(false)

    @Inject
    lateinit var cmcAPI: CmcAPI

    @Inject
    lateinit var coinsRepo: CoinsRepoDB

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getCoins(currency: String?, forceRefresh: Boolean) {
        coinsRepo.executor.submit {
            if (forceRefresh || coinsRepo.db.coins().coinsCount() == 0) {
            val disposable = cmcAPI.listings(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { remoteCoins: Listings ->
                    isRefreshing.postValue(true)
                    remoteCoins.cmcCoins.sortBy { sorting.value?.times(it.price) }
                    liveDataCoins.postValue(remoteCoins.cmcCoins)
                    isRefreshing.postValue(false)
                    coinsRepo.saveCoinsIntoDB(remoteCoins.cmcCoins)
                }
            compositeDisposable.add(disposable)
                Log.d("LOGD", "remote")
            } else {
                isRefreshing.postValue(true)
                liveDataCoins.postValue(coinsRepo.db.coins().fetchAll())
                isRefreshing.postValue(false)
                Log.d("LOGD", "db")
            }
        }

    }

}