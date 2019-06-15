package com.st18apps.testvrg.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.st18apps.testvrg.model.Result;
import com.st18apps.testvrg.network.ApiClient;
import com.st18apps.testvrg.utils.RxUtil;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<Result>> mostEmailed = new MutableLiveData<>();
    private final MutableLiveData<List<Result>> mostViewed = new MutableLiveData<>();
    private final MutableLiveData<List<Result>> mostShared = new MutableLiveData<>();

    public LiveData<List<Result>> getMostEmailed() {
        return mostEmailed;
    }

    private void setMostEmailed(List<Result> items) {
        mostEmailed.setValue(items);
    }

    public LiveData<List<Result>> getMostViewed() {
        return mostViewed;
    }

    private void setMostViewed(List<Result> items) {
        mostViewed.setValue(items);
    }

    public LiveData<List<Result>> getMostShared() {
        return mostShared;
    }

    private void setMostShared(List<Result> items) {
        mostShared.setValue(items);
    }

    public void loadMostEmailed() {

        RxUtil.networkConsumer(ApiClient.getApiInterface().getMostEmailed(30, ApiClient.API_KEY),
                newsResponse -> {
                    if (newsResponse.isStatusOK()) {
                        setMostEmailed(newsResponse.getResults());
                    }

                }, Throwable::printStackTrace);
    }

    public void loadMostViewed() {

        RxUtil.networkConsumer(ApiClient.getApiInterface().getMostViewed(30, ApiClient.API_KEY),
                newsResponse -> {
                    if (newsResponse.isStatusOK()) {
                        setMostViewed(newsResponse.getResults());
                    }

                }, Throwable::printStackTrace);
    }

    public void loadMostShared() {

        RxUtil.networkConsumer(ApiClient.getApiInterface().getMostShared(30, ApiClient.API_KEY),
                newsResponse -> {
                    if (newsResponse.isStatusOK()) {
                        setMostShared(newsResponse.getResults());
                    }

                }, Throwable::printStackTrace);
    }


}
