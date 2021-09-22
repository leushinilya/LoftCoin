package com.leushinilya.loftcoin.ui.main.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.leushinilya.loftcoin.AppModule_Companion_ContextFactory.context
import com.leushinilya.loftcoin.LoftCoin
import com.leushinilya.loftcoin.data.Coin
import com.leushinilya.loftcoin.data.CoinsRepo
import com.leushinilya.loftcoin.data.db.LoftDatabase
import com.leushinilya.loftcoin.data.remote.CmcAPI
import com.leushinilya.loftcoin.data.remote.Listings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val liveDataCoins = MutableLiveData<List<Coin>>(emptyList())
    val isRefreshing = MutableLiveData(true)
    val currency = MutableLiveData("USD")
    val message = MutableLiveData("")
    val sorting = MutableLiveData(-1)

    @Inject
    lateinit var cmcAPI: CmcAPI

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getRemoteCoins(currency: String?) {

        val disposable = cmcAPI.listings(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ remoteCoins: Listings ->
                    isRefreshing.postValue(true)
                    remoteCoins.cmcCoins.sortBy { sorting.value?.times(it.price) }
                    liveDataCoins.postValue(remoteCoins.cmcCoins)
                    isRefreshing.postValue(false)
                }) { throwable: Throwable -> message.postValue(throwable.localizedMessage) }
        compositeDisposable.add(disposable)

    }

//    //TODO: trash
//    fun getCoins(currency: String?, forceRefresh: Boolean) {
//
//        repo = app.component.coinsRepo()
//
//        if(forceRefresh || repo.listings(currency, forceRefresh).value?.size == 0){
//            getRemoteCoins(currency)
//            repo.saveCoinsIntoDB(liveDataCoins.value)
//        } else liveDataCoins.postValue(repo.listings(currency, forceRefresh).value)
//
//    }
}