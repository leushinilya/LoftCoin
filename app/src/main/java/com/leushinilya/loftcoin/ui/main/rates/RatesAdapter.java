package com.leushinilya.loftcoin.ui.main.rates;

import android.graphics.Color;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.BuildConfig;
import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.data.Coin;
import com.leushinilya.loftcoin.databinding.RateItemViewBinding;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.RatesViewHolder> {

    LayoutInflater inflater;
    List<Coin> coins = Collections.emptyList();

    public void setData(List<Coin> coins) {
        if (coins != null) this.coins = coins;
        notifyDataSetChanged();
    }

    public void updateItem(Coin coin) {
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
        if (holder.getBindingAdapterPosition() % 2 == 0) {
            holder.binding.getRoot().setBackgroundResource(R.color.rate_background);
        } else holder.binding.getRoot().setBackgroundResource(R.color.dark);
        holder.bind(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    static class RatesViewHolder extends RecyclerView.ViewHolder {

        RateItemViewBinding binding;

        public RatesViewHolder(@NonNull RateItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.rateImg.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    int minSize = Math.min(view.getWidth(), view.getHeight());
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), minSize/2f);
                }
            });
            binding.rateImg.setClipToOutline(true);
        }

        void bind(Coin coin) {
            binding.rateName.setText(coin.getSymbol());
            binding.rateVolume.setText(String.format("%.2f", coin.getPrice()));
            if (coin.getPercent() > 0) {
                binding.rateChange.setTextColor(Color.GREEN);
            } else {
                binding.rateChange.setTextColor(Color.RED);
            }
            binding.rateChange.setText(String.format("%.2f%%", coin.getPercent()));
            Picasso.get()
                    .load(BuildConfig.IMG_ENDPOINT + coin.getId() + ".png")
                    .into(binding.rateImg);
        }
    }
}

