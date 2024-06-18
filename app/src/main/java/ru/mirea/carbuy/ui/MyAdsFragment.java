package ru.mirea.carbuy.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.carbuy.R;
import ru.mirea.carbuy.adapter.MyAdsAdapter;
import ru.mirea.carbuy.databinding.FragmentMyAdsBinding;
import ru.mirea.carbuy.model.Ads;
import ru.mirea.carbuy.viewmodel.AdsViewModel;

public class MyAdsFragment extends Fragment {

    FragmentMyAdsBinding b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentMyAdsBinding.inflate(getLayoutInflater());

        b.addNewAdsFab.setOnClickListener(v -> {
            alertCreateAds(getContext());
        });

        RecyclerView recyclerView = b.adsView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        AdsViewModel adsViewModel = new ViewModelProvider(this).get(AdsViewModel.class);

        MyAdsAdapter adapter = new MyAdsAdapter(getContext(), new ArrayList<>(), new MyAdsAdapter.HandleCounterClick() {
            @Override
            public void deleteItem(Ads adsItem) {
                adsViewModel.delete(adsItem);
            }

            @Override
            public void editItem(Ads adsItem) {

            }

            @Override
            public void createItem(Ads adsItem) {

            }
        });
        recyclerView.setAdapter(adapter);

        adsViewModel.getAllAds().observe(getViewLifecycleOwner(), new Observer<List<Ads>>() {
            @Override
            public void onChanged(List<Ads> ads) {
                adapter.setAdsList(ads);
            }
        });



        return b.getRoot();
    }

    private void alertPred(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Добавить объявление");
        builder.setMessage("Максимальное количество объявлений за сегодня добавлено, попробуйте позже");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Закрытие диалога
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void alertCreateAds(Context context) {
        // Создание билдера для AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Инфлейтим наш кастомный лейаут
        View view = LayoutInflater.from(context).inflate(R.layout.create_ads, null);
        builder.setView(view);

        // Находим элементы в нашем кастомном лейауте
        EditText titleAds = view.findViewById(R.id.adsTitle);
        EditText markCarEditText = view.findViewById(R.id.markCar);
        EditText infoCarEditText = view.findViewById(R.id.infoCar);
        EditText priceCarEditText = view.findViewById(R.id.priceCar);
        EditText numTelEditText = view.findViewById(R.id.numTel);

        // Настраиваем кнопки диалогового окна
        builder.setPositiveButton("Создать", (dialog, which) -> {
            // Получаем введенные значения
            String title = titleAds.getText().toString();
            String markCar = markCarEditText.getText().toString();
            String infoCar = infoCarEditText.getText().toString();
            String priceCar = priceCarEditText.getText().toString();
            String numTel = numTelEditText.getText().toString();

            // Проверяем, чтобы все поля были заполнены
            if (markCar.isEmpty() || priceCar.isEmpty() || numTel.isEmpty()) {
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            // Создаем новое объявление
            Ads newAds = new Ads(title, markCar, infoCar, priceCar, numTel);

            // Вызываем метод ViewModel для сохранения нового объявления
            AdsViewModel adsViewModel = new ViewModelProvider((FragmentActivity) context).get(AdsViewModel.class);
            adsViewModel.create(newAds);

            Toast.makeText(context, "Объявление создано", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> {
            dialog.dismiss();
        });

        // Показываем диалоговое окно
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}