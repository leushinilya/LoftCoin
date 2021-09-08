package com.leushinilya.loftcoin.ui.main.rates;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leushinilya.loftcoin.data.CmcAPI;
import com.leushinilya.loftcoin.data.Coin;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RatesViewModel extends ViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<List<Coin>> liveDataCoins = new MutableLiveData<>(Collections.emptyList());
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>(true);
    public MutableLiveData<String> message = new MutableLiveData<>("");

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public void getRemoteCoins(CmcAPI cmcAPI) {

        Disposable disposable = (cmcAPI.listings("USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(remoteCoins -> {
                    liveDataCoins.postValue(remoteCoins.getCoins());
                    isRefreshing.postValue(false);
                }, throwable -> {
                    message.postValue(throwable.getLocalizedMessage());
                }));

        compositeDisposable.add(disposable);
    }

}
