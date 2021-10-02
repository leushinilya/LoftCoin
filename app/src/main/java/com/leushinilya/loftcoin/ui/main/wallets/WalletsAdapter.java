package com.leushinilya.loftcoin.ui.main.wallets;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.BuildConfig;
import com.leushinilya.loftcoin.data.wallets.Wallet;
import com.leushinilya.loftcoin.databinding.CardViewBinding;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.CardsViewHolder>{

    LayoutInflater inflater;
    List<Wallet> wallets = Collections.emptyList();

    public void setData(List<Wallet> wallets){
        if(wallets != null) this.wallets = wallets;
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardsViewHolder(CardViewBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        holder.bind(wallets.get(position));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    static class CardsViewHolder extends RecyclerView.ViewHolder{

        CardViewBinding binding;

        public CardsViewHolder(CardViewBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(Wallet wallet){
            binding.currencyValue.setText((int) wallet.getBalance());
            Picasso.get()
                    .load(BuildConfig.IMG_ENDPOINT + wallet.getCoin().getId() + ".png")
                    .into(binding.currencyImg);
        }
    }
}
