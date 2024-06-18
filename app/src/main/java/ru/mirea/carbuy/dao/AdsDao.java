package ru.mirea.carbuy.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.mirea.carbuy.model.Ads;


@Dao
public interface AdsDao {
    @Query("SELECT * FROM ads")
    LiveData<List<Ads>> getAllSaums();

    @Insert
    void insertAll(Ads...adss);

    @Insert
    void insertAds(Ads adss);

    @Update
    void updateAds(Ads adss);

    @Delete
    void deleteAds(Ads adss);
}

