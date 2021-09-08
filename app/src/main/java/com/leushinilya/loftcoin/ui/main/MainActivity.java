package com.leushinilya.loftcoin.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController controller = Navigation.findNavController(this, R.id.main_host);
        NavigationUI.setupWithNavController(binding.toolbar, controller, new AppBarConfiguration
                .Builder(binding.bottomNav.getMenu())
                .build());
        NavigationUI.setupWithNavController(binding.bottomNav, controller);

    }
}