package com.leushinilya.loftcoin.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.ui.welcome.WelcomeActivity;
import com.leushinilya.loftcoin.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Handler handler = new Handler();
    Runnable goNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true)) {
            goNext = ()-> startActivity(new Intent(this, WelcomeActivity.class));
        } else {
            goNext = () -> startActivity(new Intent(this, MainActivity.class));
        }
        handler.postDelayed(goNext, 1500);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(goNext);
    }
}