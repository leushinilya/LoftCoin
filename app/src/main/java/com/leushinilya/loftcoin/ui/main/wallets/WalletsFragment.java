package com.leushinilya.loftcoin.ui.main.wallets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leushinilya.loftcoin.AppComponent;
import com.leushinilya.loftcoin.LoftCoin;
import com.leushinilya.loftcoin.R;
import com.leushinilya.loftcoin.databinding.FragmentWalletsBinding;

public class WalletsFragment extends Fragment {

    FragmentWalletsBinding binding;
    WalletsViewModel walletsViewModel;
    AppComponent component;
    WalletsAdapter adapter = new WalletsAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = ((LoftCoin)getActivity().getApplication()).component;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWalletsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        walletsViewModel = new ViewModelProvider(this).get(WalletsViewModel.class);
        component.inject(walletsViewModel);

        walletsViewModel.getWallets().observe(getViewLifecycleOwner(), wallets -> adapter.setData(wallets));
//        walletsViewModel.refreshWallets();

        binding.cardsRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.cardsRecycler.setLayoutManager(layoutManager);

        final int displayWidth = getActivity().getResources().getDisplayMetrics().widthPixels;
        int cardWidth = (int)getResources().getDimension(R.dimen.cardWidth);
        int padding = (displayWidth - cardWidth) / 2;
        binding.cardsRecycler.setPadding(padding,0,padding,0);
        binding.cardsRecycler.setClipToPadding(false);

        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.cardsRecycler);

        binding.cardsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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