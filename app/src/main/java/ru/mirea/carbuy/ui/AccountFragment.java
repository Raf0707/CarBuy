package ru.mirea.carbuy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import ru.mirea.carbuy.R;
import ru.mirea.carbuy.databinding.FragmentAccountBinding;
import ru.mirea.carbuy.model.Users;

public class AccountFragment extends Fragment {
    FragmentAccountBinding b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentAccountBinding.inflate(getLayoutInflater());
        TextView userName = b.UserName;
        Button exitButton = b.exitBtn;
        Button adminButton = b.adminBtn;

        userName.setText(Users.login_name);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitAccount();
                ProfileFragment profileFragment2Fragment = new ProfileFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, profileFragment2Fragment);
                transaction.commit();
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Users.root == "admin"){
                    AdminPanelFragment adminPanelFragment = new AdminPanelFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, adminPanelFragment);
                    transaction.commit();
                }else{
                    Toast.makeText(getActivity(), "Не доступно", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return b.getRoot();
    }

    private void ExitAccount(){
        Users.isUserAuthorized=false;
        Users.login_name="";
        Toast.makeText(getActivity(), "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show();
    }
}