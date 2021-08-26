package com.leushinilya.loftcoin;

import android.app.Application;
import android.os.StrictMode;

public class LoftCoin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            StrictMode.enableDefaults();
        }

    }
}
