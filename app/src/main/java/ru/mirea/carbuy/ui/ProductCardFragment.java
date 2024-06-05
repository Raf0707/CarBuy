package ru.mirea.carbuy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ru.mirea.carbuy.databinding.FragmentProductCardBinding;

public class ProductCardFragment extends Fragment {
    FragmentProductCardBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentProductCardBinding.inflate(getLayoutInflater());

        if (getArguments() != null) {
            String carName = getArguments().getString("carName");
            String carPrice = getArguments().getString("carPrice");
            int carImageRes = getArguments().getInt("carImage");

            b.markCarTitle.setText("Марка: " + carName);
            b.price.setText("Цена: " + carPrice);
            b.carImage.setImageResource(carImageRes);
        }


        return b.getRoot();
    }
}