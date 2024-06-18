package ru.mirea.carbuy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.carbuy.R;
import ru.mirea.carbuy.model.Car;

public class SearchFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private DatabaseReference mDataBase;
    private String Car_name;
    private Spinner spinner;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Button search_information = view.findViewById(R.id.search_btn);
        spinner = view.findViewById(R.id.spinnerCar);

        listView = view.findViewById(R.id.list_information);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        search_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Car_name = spinner.getSelectedItem().toString();
                mDataBase = FirebaseDatabase.getInstance("https://carbuy-122d6-default-rtdb.firebaseio.com/").getReference(Car_name);
                getDataFromDB();
            }
        });

        // Добавляем обработчик кликов на элементы списка
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = listData.get(position);

            // Найти индекс начала и конца скобок
            int startIndex = selectedItem.indexOf('(');
            int endIndex = selectedItem.indexOf(')');

            // Извлечь марку машины и цену
            String carName = selectedItem.substring(0, startIndex).trim();
            String carPrice = selectedItem.substring(startIndex + 1, endIndex).trim();
            int imRes = 0;

            if (selectedItem.equals("Nissan GT-R (7 626 000 руб.)")) {
                imRes = R.drawable.nissan_gtr;
            } else if (selectedItem.equals("Nissan Qashqai (1 555 000 руб.)")) {
                imRes = R.drawable.nissan_qashkai;
            } else if (selectedItem.equals("Nissan 370z nismo (1 925 123 руб.)")) {
                imRes = R.drawable.z370;
            } else if (selectedItem.equals("BMW M5 (9 400 000 руб.) ")) {
                imRes = R.drawable.m5bmw;
            } else if (selectedItem.equals("BMW 320i xDrive (3 330 000 руб.)")) {
                imRes = R.drawable.i320bmw;
            } else if (selectedItem.equals("BMW X6 (6 650 000 руб.)")) {
                imRes = R.drawable.x6bmw;
            } else if (selectedItem.equals("BMW 1 (1 600 000 руб.)")) {
                imRes = R.drawable.bmw1;
            }

            Bundle bundle = new Bundle();
            bundle.putString("carName", carName);
            bundle.putString("carPrice", carPrice);
            bundle.putInt("carImage", imRes);

            ProductCardFragment productCardFragment = new ProductCardFragment();
            productCardFragment.setArguments(bundle);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, productCardFragment);
            transaction.addToBackStack(null);
            transaction.commit();


            System.out.println(selectedItem);

            /*
            Nissan GT-R (7 626 000 руб.)
            Nissan Qashqai (1 555 000 руб.)
            Nissan 370z nismo (1 925 123 руб.)

            BMW M5 (9 400 000 руб.)
            BMW 320i xDrive (3 330 000 руб.)
            BMW X6 (6 650 000 руб.)
            BMW 1 (1 600 000 руб.)
             */
        });

        return view;
    }

    private void getDataFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listData.size() > 0) listData.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Car car = ds.getValue(Car.class);
                    assert car != null;
                    listData.add(car.name);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }
}
