package ru.mirea.carbuy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.mirea.carbuy.model.Ads;
import ru.mirea.carbuy.repo.AdsRepository;

public class AdsViewModel extends AndroidViewModel {
    private AdsRepository repository;
    private LiveData<List<Ads>> allAds;

    public AdsViewModel(@NonNull Application application) {
        super(application);
        repository = new AdsRepository(application);
        allAds = repository.getAllAds();
    }

    public LiveData<List<Ads>> getAllAds() {
        return allAds;
    }

    public void insert(Ads ads) {
        repository.insert(ads);
    }

    public void update(Ads ads) {
        repository.update(ads);
    }

    public void delete(Ads ads) {
        repository.delete(ads);
    }

    public void create(Ads ads) {
        repository.insert(ads);
    }
}


