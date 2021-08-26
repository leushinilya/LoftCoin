package com.leushinilya.loftcoin.ui.welcome;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.WelcomePageBinding;

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.ViewHolder> {

    LayoutInflater inflater;

    static int[] IMAGES = {
            R.drawable.welcome_img_1,
            R.drawable.welcome_img_2,
            R.drawable.welcome_img_3
    };

    static int[] TITLES = {
            R.string.welcome_title_1,
            R.string.welcome_title_2,
            R.string.welcome_title_3
    };

    static int[] TEXTS = {
            R.string.welcome_text_1,
            R.string.welcome_text_2,
            R.string.welcome_text_3
    };

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(WelcomePageBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.welcomeImg.setImageResource(IMAGES[position]);
        holder.binding.welcomeTitle.setText(TITLES[position]);
        holder.binding.welcomeText.setText(TEXTS[position]);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        WelcomePageBinding binding;

        public ViewHolder(@NonNull WelcomePageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

