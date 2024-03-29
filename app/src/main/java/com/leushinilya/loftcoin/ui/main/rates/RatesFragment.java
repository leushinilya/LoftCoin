package com.leushinilya.loftcoin.ui.main.rates;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.AppComponent;
import com.leushinilya.loftcoin.LoftCoin;
import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.FragmentRatesBinding;

public class RatesFragment extends Fragment {

    FragmentRatesBinding binding;
    RatesViewModel ratesViewModel;
    RatesAdapter adapter;
    Menu menu;
    AppComponent component;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = ((LoftCoin)getActivity().getApplication()).component;
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
        setHasOptionsMenu(true);

        adapter = new RatesAdapter();
        binding.ratesRecycler.setAdapter(adapter);
        binding.ratesRecycler.setLayoutManager
                (new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        ratesViewModel = new ViewModelProvider(this).get(RatesViewModel.class);
        component.inject(ratesViewModel);

        ratesViewModel.getLiveDataCoins().observe(getViewLifecycleOwner(), coins -> adapter.setData(coins));

//        change currency
        ratesViewModel.getCurrency().observe(getViewLifecycleOwner(), currency -> {
            if(menu!=null && currency.equals("USD")) menu.getItem(0).setIcon(R.drawable.ic_usd);
            if(menu!=null && currency.equals("EUR")) menu.getItem(0).setIcon(R.drawable.ic_eur);
            if(menu!=null && currency.equals("RUB")) menu.getItem(0).setIcon(R.drawable.ic_rub);
            if(ratesViewModel.isViewCreated().getValue()){
                ratesViewModel.refreshCoins(ratesViewModel.getCurrency().getValue(), true);
            }
        });

//        change sorting direction
        ratesViewModel.getSorting().observe(getViewLifecycleOwner(), sorting ->{
            if(menu!=null && sorting==-1) menu.getItem(1).setIcon(R.drawable.ic_sort_down);
            if(menu!=null && sorting==1) menu.getItem(1).setIcon(R.drawable.ic_sort_up);
            if(ratesViewModel.isViewCreated().getValue()) {
                ratesViewModel.refreshCoins(ratesViewModel.getCurrency().getValue(), true);
            }
        });

//        manually refreshing
        ratesViewModel.isRefreshing().observe(getViewLifecycleOwner(),
                isRefreshing -> binding.ratesRefresh.setRefreshing(isRefreshing));
        binding.ratesRefresh.setOnRefreshListener(()
                -> ratesViewModel.refreshCoins(ratesViewModel.getCurrency().getValue(), true));

//        first invocation
        ratesViewModel.refreshCoins(ratesViewModel.getCurrency().getValue(), false);
        ratesViewModel.isViewCreated().postValue(true);
        Log.d("LOGD", "onViewCreated");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.rates_menu, menu);
        this.menu = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.currencyDialog) {
            CurrencyDialog dialog = new CurrencyDialog();
            dialog.show(getChildFragmentManager(), "");
            return true;
        }
        else if (item.getItemId() == R.id.sort){
            ratesViewModel.getSorting().postValue(ratesViewModel.getSorting().getValue() * -1);
        }
        return super.onOptionsItemSelected(item);
    }
}