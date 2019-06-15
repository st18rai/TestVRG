package com.st18apps.testvrg.network;

import com.google.gson.annotations.SerializedName;
import com.st18apps.testvrg.model.Result;

import java.util.List;

public class NewsResponse {
    private String status;
    private String copyright;
    @SerializedName("num_results")
    private int numResults;
    private List<Result> results = null;

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public int getNumResults() {
        return numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public boolean isStatusOK(){
        return status.equals("OK");
    }
}
