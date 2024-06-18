package ru.mirea.carbuy;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class App extends Application {
    public static App instance = null;

    public static App getInstance() {
        if (instance == null)
            instance = new App();
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}