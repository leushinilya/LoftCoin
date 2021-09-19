package com.leushinilya.loftcoin;

import android.app.Application;
import android.os.StrictMode;
public class LoftCoin extends Application {

    public AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            StrictMode.enableDefaults();
        }

        component = DaggerAppComponent.builder()
                .build();
        System.out.println(component);
    }

}
