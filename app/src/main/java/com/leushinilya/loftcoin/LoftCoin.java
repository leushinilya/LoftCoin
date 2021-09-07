package com.leushinilya.loftcoin;

import android.app.Application;
import android.os.StrictMode;

import com.leushinilya.loftcoin.data.CmcAPI;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoftCoin extends Application {

    public CmcAPI cmcAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            StrictMode.enableDefaults();
        }
        configureRetrofit();
    }

    private void configureRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

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

        cmcAPI = retrofit.create(CmcAPI.class);

    }
}
