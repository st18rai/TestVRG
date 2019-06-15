package com.st18apps.testvrg.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.st18apps.testvrg.db.NewsDatabase;
import com.st18apps.testvrg.interfaces.NewsDao;
import com.st18apps.testvrg.model.NewsData;

import java.util.List;

public class NewsRepository {
    private NewsDao newsDao;
    private LiveData<List<NewsData>> allFavoriteNews;

    public NewsRepository(Application application) {

        NewsDatabase database = NewsDatabase.getInstance(application);
        newsDao = database.newsDao();
        allFavoriteNews = newsDao.getAllFavoriteNews();
    }

    public void insert(NewsData newsData) {
        new InsertNewsAsyncTask(newsDao).execute(newsData);
    }

    public void update(NewsData newsData) {
        new UpdateNewsAsyncTask(newsDao).execute(newsData);
    }

    public void delete(NewsData newsData) {
        new DeleteNewsAsyncTask(newsDao).execute(newsData);
    }

    public void deleteAll() {
        new DeleteAllNewsAsyncTask(newsDao).execute();
    }

    public LiveData<List<NewsData>> getAllFavoriteNews() {
        return allFavoriteNews;
    }

    private static class InsertNewsAsyncTask extends AsyncTask<NewsData, Void, Void> {

        private NewsDao newsDao;

        public InsertNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsData... newsData) {
            newsDao.insert(newsData[0]);
            return null;
        }
    }

    private static class UpdateNewsAsyncTask extends AsyncTask<NewsData, Void, Void> {

        private NewsDao newsDao;

        public UpdateNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsData... newsData) {
            newsDao.update(newsData[0]);
            return null;
        }
    }

    private static class DeleteNewsAsyncTask extends AsyncTask<NewsData, Void, Void> {

        private NewsDao newsDao;

        public DeleteNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsData... newsData) {
            newsDao.delete(newsData[0]);
            return null;
        }
    }

    private static class DeleteAllNewsAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsDao newsDao;

        public DeleteAllNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteAll();
            return null;
        }
    }


}
