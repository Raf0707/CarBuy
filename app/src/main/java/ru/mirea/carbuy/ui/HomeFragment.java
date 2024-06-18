package ru.mirea.carbuy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ru.mirea.carbuy.R;
import ru.mirea.carbuy.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentHomeBinding.inflate(getLayoutInflater());

        b.myAds.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyAdsFragment())
                    .commit();
        });

        return b.getRoot();
    }
}