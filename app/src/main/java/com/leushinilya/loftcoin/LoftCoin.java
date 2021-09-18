package com.leushinilya.loftcoin;

import android.app.Application;
import android.os.StrictMode;
import com.leushinilya.loftcoin.data.CmcAPI;
import com.leushinilya.loftcoin.di.components.AppComponent;
import com.leushinilya.loftcoin.di.components.DaggerAppComponent;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoftCoin extends Application {

    public AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            StrictMode.enableDefaults();
        }

        component = DaggerAppComponent.builder()
                .application(this)
                .build();
    }

}
