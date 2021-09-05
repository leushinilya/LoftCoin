package com.leushinilya.loftcoin.ui.main.rates;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.data.Coin;
import com.leushinilya.loftcoin.databinding.FragmentRatesBinding;
import com.leushinilya.loftcoin.ui.main.wallets.CardsAdapter;

import java.util.List;

public class RatesFragment extends Fragment implements RatesView{

    RatesPresenter presenter;

    FragmentRatesBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RatesPresenter();
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
        binding.ratesRecycler.setAdapter(new RatesAdapter());
        binding.ratesRecycler.setLayoutManager
                (new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        presenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach(this);
    }

    @Override
    public void showCoins(List<Coin> coinList) {
        ((RatesAdapter)binding.ratesRecycler.getAdapter()).setData(coinList);
    }

    @Override
    public void showError(String error) {

    }
}