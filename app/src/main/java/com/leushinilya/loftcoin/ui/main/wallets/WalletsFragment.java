package com.leushinilya.loftcoin.ui.main.wallets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.FragmentWalletsBinding;

public class WalletsFragment extends Fragment {

    FragmentWalletsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cardsRecyclerView.setAdapter(new CardsAdapter());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.cardsRecyclerView.setLayoutManager(layoutManager);
        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.cardsRecyclerView);
    }
}