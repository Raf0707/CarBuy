package ru.mirea.carbuy.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.mirea.carbuy.dao.AdsDao;
import ru.mirea.carbuy.database.AdsDatabase;
import ru.mirea.carbuy.model.Ads;

public class AdsRepository {
    private AdsDao adsDao;
    private LiveData<List<Ads>> allAds;

    public AdsRepository(Application application) {
        AdsDatabase db = AdsDatabase.getInstance(application);
        adsDao = db.adsDao();
        allAds = adsDao.getAllSaums();
    }

    public LiveData<List<Ads>> getAllAds() {
        return allAds;
    }

    public void insert(Ads ads) {
        new InsertAdsAsyncTask(adsDao).execute(ads);
    }

    public void update(Ads ads) {
        new UpdateAdsAsyncTask(adsDao).execute(ads);
    }

    public void delete(Ads ads) {
        new DeleteAdsAsyncTask(adsDao).execute(ads);
    }

    private static class InsertAdsAsyncTask extends AsyncTask<Ads, Void, Void> {
        private AdsDao adsDao;

        private InsertAdsAsyncTask(AdsDao adsDao) {
            this.adsDao = adsDao;
        }

        @Override
        protected Void doInBackground(Ads... ads) {
            adsDao.insertAds(ads[0]);
            return null;
        }
    }

    private static class UpdateAdsAsyncTask extends AsyncTask<Ads, Void, Void> {
        private AdsDao adsDao;

        private UpdateAdsAsyncTask(AdsDao adsDao) {
            this.adsDao = adsDao;
        }

        @Override
        protected Void doInBackground(Ads... ads) {
            adsDao.updateAds(ads[0]);
            return null;
        }
    }

    private static class DeleteAdsAsyncTask extends AsyncTask<Ads, Void, Void> {
        private AdsDao adsDao;

        private DeleteAdsAsyncTask(AdsDao adsDao) {
            this.adsDao = adsDao;
        }

        @Override
        protected Void doInBackground(Ads... ads) {
            adsDao.deleteAds(ads[0]);
            return null;
        }
    }
}
