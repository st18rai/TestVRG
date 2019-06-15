package com.st18apps.testvrg.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    private String url;
    private String subsection;
    @SerializedName("email_count")
    private int emailCount;
    @SerializedName("count_type")
    private String countType;
    private String column;
    private String section;
    private long id;
    @SerializedName("asset_id")
    private long assetId;
    private String nytdsection;
    private String type;
    private String title;
    @SerializedName("published_date")
    private String publishedDate;
    private String source;
    private String updated;
    private List<Medium> media = null;
    private String uri;

    public String getUrl() {
        return url;
    }

    public String getSubsection() {
        return subsection;
    }

    public int getEmailCount() {
        return emailCount;
    }

    public String getCountType() {
        return countType;
    }

    public String getColumn() {
        return column;
    }

    public String getSection() {
        return section;
    }

    public long getId() {
        return id;
    }

    public long getAssetId() {
        return assetId;
    }

    public String getNytdsection() {
        return nytdsection;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getSource() {
        return source;
    }

    public String getUpdated() {
        return updated;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public String getUri() {
        return uri;
    }
}
