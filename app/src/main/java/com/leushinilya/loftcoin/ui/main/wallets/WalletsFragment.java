package com.leushinilya.loftcoin.ui.main.wallets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.FragmentWalletsBinding;

public class WalletsFragment extends Fragment {

    FragmentWalletsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cardsRecyclerView.setAdapter(new CardsAdapter());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.cardsRecyclerView.setLayoutManager(layoutManager);

        final int displayWidth = getActivity().getResources().getDisplayMetrics().widthPixels;
        int cardWidth = (int)getResources().getDimension(R.dimen.cardWidth);
        int padding = (displayWidth - cardWidth) / 2;
        binding.cardsRecyclerView.setPadding(padding,0,padding,0);
        binding.cardsRecyclerView.setClipToPadding(false);

        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.cardsRecyclerView);

        binding.cardsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int centerX = (recyclerView.getLeft() + recyclerView.getRight()) / 2;
                for(int i = 0; i<recyclerView.getChildCount(); ++i){
                    View child = recyclerView.getChildAt(i);
                    int childCenterX = (child.getLeft() + child.getRight()) / 2;
                    float childOffset = Math.abs(centerX - childCenterX) / (float)centerX;
                    float factor = (float) Math.pow(0.85, childOffset);
                    child.setScaleX(factor);
                    child.setScaleY(factor);
                }
            }
        });
    }
}