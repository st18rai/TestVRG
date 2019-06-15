package com.st18apps.testvrg.network;

import com.google.gson.annotations.SerializedName;
import com.st18apps.testvrg.model.NewsData;

import java.util.List;

public class NewsResponse {
    private String status;
    private String copyright;
    @SerializedName("num_results")
    private int numResults;
    @SerializedName("results")
    private List<NewsData> newsDataList = null;

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public int getNumResults() {
        return numResults;
    }

    public List<NewsData> getResults() {
        return newsDataList;
    }

    public boolean isStatusOK(){
        return status.equals("OK");
    }
}
