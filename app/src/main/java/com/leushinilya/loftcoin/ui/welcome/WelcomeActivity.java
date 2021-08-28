package com.leushinilya.loftcoin.ui.welcome;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.ActivityWelcomeBinding;
import com.leushinilya.loftcoin.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    public static final String KEY_SHOW_WELCOME = "show_welcome";
    ActivityWelcomeBinding binding;
    PagerSnapHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.welcomeRecyclerview.setAdapter(new WelcomeAdapter());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.welcomeRecyclerview.setLayoutManager(layoutManager);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.welcomeRecyclerview);
        binding.welcomeButton.setOnClickListener(v -> {
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putBoolean(KEY_SHOW_WELCOME, false)
                    .apply();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        binding.welcomeRecyclerview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                configureIndicator(layoutManager.findFirstVisibleItemPosition());
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.attachToRecyclerView(null);
        binding.welcomeRecyclerview.setAdapter(null);
    }

    private void configureIndicator(int position) {
        int[] INDICATOR_IMAGES = {
                R.drawable.welcome_indicator_1,
                R.drawable.welcome_indicator_2,
                R.drawable.welcome_indicator_3,
        };
        binding.welcomeIndicator.setImageResource(INDICATOR_IMAGES[position]);
    }

}