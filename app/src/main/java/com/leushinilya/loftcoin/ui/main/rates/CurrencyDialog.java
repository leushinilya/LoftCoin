package com.leushinilya.loftcoin.ui.main.rates;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.leushinilya.loftcoin.LoftCoin;
import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.CurrencyDialogBinding;

public class CurrencyDialog extends AppCompatDialogFragment {

    CurrencyDialogBinding binding;
    RatesViewModel ratesViewModel;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.equals(binding.usd)) ratesViewModel.getCurrency().postValue("USD");
            else if(v.equals(binding.eur)) ratesViewModel.getCurrency().postValue("EUR");
            else if(v.equals(binding.rub)) ratesViewModel.getCurrency().postValue("RUB");
            dismiss();
        }
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = CurrencyDialogBinding.inflate(requireActivity().getLayoutInflater());
        binding.usd.setOnClickListener(listener);
        binding.eur.setOnClickListener(listener);
        binding.rub.setOnClickListener(listener);
        ratesViewModel = ((RatesFragment)getParentFragment()).ratesViewModel;
        return new MaterialAlertDialogBuilder(requireActivity(), R.style.CurrencyDialog)
                .setView(binding.getRoot())
                .setTitle(R.string.choose_currency)
                .create();
    }

}
