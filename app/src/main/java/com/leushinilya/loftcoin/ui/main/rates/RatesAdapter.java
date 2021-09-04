package com.leushinilya.loftcoin.ui.main.rates;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.RateItemViewBinding;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.RatesViewHolder> {

    LayoutInflater inflater;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    @NonNull
    @Override
    public RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatesViewHolder(RateItemViewBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatesViewHolder holder, int position) {
        if(holder.getBindingAdapterPosition()%2 == 0){
            holder.binding.getRoot().setBackgroundResource(R.color.rate_background);
        } else holder.binding.getRoot().setBackgroundResource(R.color.dark);
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    static class RatesViewHolder extends RecyclerView.ViewHolder{

        RateItemViewBinding binding;

        public RatesViewHolder(@NonNull RateItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

