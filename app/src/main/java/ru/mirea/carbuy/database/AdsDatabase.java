package ru.mirea.carbuy.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.mirea.carbuy.dao.AdsDao;
import ru.mirea.carbuy.model.Ads;


@Database(entities = {Ads.class}, version = 1)
public abstract class AdsDatabase extends RoomDatabase {
    public abstract AdsDao adsDao();

    private static AdsDatabase INSTANCE;

    public static synchronized AdsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AdsDatabase.class, "ads_database")
                    .allowMainThreadQueries()
                    //.setJournalMode(JournalMode.AUTOMATIC)
                    //.fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
