package com.st18apps.testvrg.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.st18apps.testvrg.model.NewsData;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(NewsData result);

    @Update
    void update(NewsData result);

    @Delete
    void delete(NewsData newsData);

    @Query("DELETE FROM news_table")
    void deleteAll();

    @Query("SELECT * FROM news_table ORDER BY title ASC")
    LiveData<List<NewsData>> getAllFavoriteNews();
}

