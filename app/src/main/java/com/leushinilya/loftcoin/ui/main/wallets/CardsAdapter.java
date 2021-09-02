package com.leushinilya.loftcoin.ui.main.wallets;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.databinding.CardViewBinding;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder>{

    LayoutInflater inflater;

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

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class CardsViewHolder extends RecyclerView.ViewHolder{

        CardViewBinding binding;

        public CardsViewHolder(CardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
