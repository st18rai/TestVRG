package com.st18apps.testvrg.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.st18apps.testvrg.model.NewsData;
import com.st18apps.testvrg.network.ApiClient;
import com.st18apps.testvrg.repository.NewsRepository;
import com.st18apps.testvrg.utils.RxUtil;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;

    private final LiveData<List<NewsData>> favoritesNews;
    private final MutableLiveData<String> status = new MutableLiveData<>();

    private final MutableLiveData<List<NewsData>> mostEmailed = new MutableLiveData<>();
    private final MutableLiveData<List<NewsData>> mostViewed = new MutableLiveData<>();
    private final MutableLiveData<List<NewsData>> mostShared = new MutableLiveData<>();
    private final MutableLiveData<NewsData> selected = new MutableLiveData<>();

    public NewsViewModel(@NonNull Application application) {
        super(application);

        newsRepository = new NewsRepository(application);
        favoritesNews = newsRepository.getAllFavoriteNews();
    }

    private void setStatus(String statusNew) {
        status.setValue(statusNew);
    }
    public LiveData<String> getStatus() {
        return status;
    }

    public void select(NewsData item) {
        selected.setValue(item);
    }
    public LiveData<NewsData> getSelected() {
        return selected;
    }

    public LiveData<List<NewsData>> getMostEmailed() {
        return mostEmailed;
    }
    private void setMostEmailed(List<NewsData> items) {
        mostEmailed.setValue(items);
    }

    public LiveData<List<NewsData>> getMostViewed() {
        return mostViewed;
    }
    private void setMostViewed(List<NewsData> items) {
        mostViewed.setValue(items);
    }

    public LiveData<List<NewsData>> getMostShared() {
        return mostShared;
    }
    private void setMostShared(List<NewsData> items) {
        mostShared.setValue(items);
    }

    // network methods

    public void loadMostEmailed() {

        RxUtil.networkConsumer(ApiClient.getApiInterface().getMostEmailed(30, ApiClient.API_KEY),
                newsResponse -> {
                    if (newsResponse.isStatusOK()) {
                        setMostEmailed(newsResponse.getResults());
                    } else {
                        setStatus(newsResponse.getStatus());
                    }

                }, throwable -> {
                    throwable.printStackTrace();
                    setStatus("Something went wrong");
                });
    }

    public void loadMostViewed() {

        RxUtil.networkConsumer(ApiClient.getApiInterface().getMostViewed(30, ApiClient.API_KEY),
                newsResponse -> {
                    if (newsResponse.isStatusOK()) {
                        setMostViewed(newsResponse.getResults());
                    } else {
                        setStatus(newsResponse.getStatus());
                    }

                }, throwable -> {
                    throwable.printStackTrace();
                    setStatus("Something went wrong");
                });
    }

    public void loadMostShared() {

        RxUtil.networkConsumer(ApiClient.getApiInterface().getMostShared(30, ApiClient.API_KEY),
                newsResponse -> {
                    if (newsResponse.isStatusOK()) {
                        setMostShared(newsResponse.getResults());
                    } else {
                        setStatus(newsResponse.getStatus());
                    }

                }, throwable -> {
                    throwable.printStackTrace();
                    setStatus("Something went wrong");
                });
    }

    // db methods

    public void insert(NewsData newsData) {
        newsRepository.insert(newsData);
    }

    public void update(NewsData newsData) {
        newsRepository.update(newsData);
    }

    public void delete(NewsData newsData) {
        newsRepository.delete(newsData);
    }

    public void deleteAll() {
        newsRepository.deleteAll();
    }

    public LiveData<List<NewsData>> getFavoritesNews() {
        return favoritesNews;
    }
}
