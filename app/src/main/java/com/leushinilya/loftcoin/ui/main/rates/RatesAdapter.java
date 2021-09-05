package com.leushinilya.loftcoin.ui.main.rates;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.data.Coin;
import com.leushinilya.loftcoin.databinding.RateItemViewBinding;

import java.util.Collections;
import java.util.List;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.RatesViewHolder> {

    LayoutInflater inflater;
    List<Coin> coins = Collections.emptyList();

    public void setData(List<Coin> coins){
        if(coins!=null) this.coins = coins;
        notifyDataSetChanged();
    }

    public void updateItem(Coin coin){
        notifyItemChanged(coins.indexOf(coin));
    }

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
        holder.bind(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    static class RatesViewHolder extends RecyclerView.ViewHolder{

        RateItemViewBinding binding;

        public RatesViewHolder(@NonNull RateItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Coin coin){
            binding.rateName.setText(coin.getSymbol());
        }
    }
}

