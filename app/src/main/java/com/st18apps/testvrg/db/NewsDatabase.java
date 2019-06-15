package com.st18apps.testvrg.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.st18apps.testvrg.interfaces.NewsDao;
import com.st18apps.testvrg.model.NewsData;

@Database(entities = NewsData.class, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase instance;

    public abstract NewsDao newsDao();

    public static synchronized NewsDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NewsDatabase.class, "news_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
