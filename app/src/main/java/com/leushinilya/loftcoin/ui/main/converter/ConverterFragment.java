package com.leushinilya.loftcoin.ui.main.converter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.FragmentConverterBinding;

public class ConverterFragment extends Fragment {

    FragmentConverterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConverterBinding.inflate(inflater);
        return binding.getRoot();
    }
}