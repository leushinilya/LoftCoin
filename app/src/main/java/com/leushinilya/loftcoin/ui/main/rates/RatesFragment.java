package com.leushinilya.loftcoin.ui.main.rates;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leushinilya.loftcoin.LoftCoin;
import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.data.Coin;
import com.leushinilya.loftcoin.databinding.FragmentRatesBinding;
import com.leushinilya.loftcoin.ui.main.wallets.CardsAdapter;

import java.util.List;

public class RatesFragment extends Fragment{

    FragmentRatesBinding binding;
    RatesViewModel ratesViewModel;
    RatesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRatesBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RatesAdapter();
        binding.ratesRecycler.setAdapter(adapter);
        binding.ratesRecycler.setLayoutManager
                (new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        ratesViewModel = new ViewModelProvider(this).get(RatesViewModel.class);
        ratesViewModel.liveDataCoins.observe(getViewLifecycleOwner(), new Observer<List<Coin>>() {
            @Override
            public void onChanged(List<Coin> coins) {
                adapter.setData(coins);
            }
        });

        ratesViewModel.getRemoteCoins(((LoftCoin)(getActivity().getApplication())).cmcAPI);

    }


}