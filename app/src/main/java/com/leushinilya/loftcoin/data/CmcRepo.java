package com.leushinilya.loftcoin.data;

import com.leushinilya.loftcoin.BuildConfig;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class CmcRepo {

    public CmcAPI cmcAPI;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CmcRepo() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    final Request request = chain.request();
                    return chain.proceed(request.newBuilder()
                            .addHeader(CmcAPI.API_KEY, BuildConfig.API_KEY)
                            .build());
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pro-api.coinmarketcap.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.cmcAPI = retrofit.create(CmcAPI.class);
    }

}
