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
            Bundle bundle = new Bundle();
            /*if (selectedItem.equals(""))
            bundle.putString("carName", selectedItem);
            bundle.putString("carPrice", "Цена: 10000$");
            bundle.putInt("carImage", R.drawable.nissan_qashqai);*/

            /*ProductCardFragment productCardFragment = new ProductCardFragment();
            productCardFragment.setArguments(bundle);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, productCardFragment);
            transaction.addToBackStack(null);
            transaction.commit();*/
            System.out.println(selectedItem.toString());
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
