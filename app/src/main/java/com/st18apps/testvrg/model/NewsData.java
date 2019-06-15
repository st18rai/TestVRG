package com.st18apps.testvrg.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "news_table")
public class NewsData {
    @PrimaryKey
    private long id;
    private String url;
    private String title;
    @SerializedName("published_date")
    private String publishedDate;
    @Ignore
    private List<Medium> media = null;
    private boolean liked;
    private String imageUrl;

    public NewsData(long id, String url, String title, String publishedDate, String imageUrl, boolean liked) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.publishedDate = publishedDate;
        this.imageUrl = imageUrl;
        this.liked = liked;
    }

    public String getImageUrl() {

        if (media != null){
            imageUrl = media.get(0).getMediaMetadata().get(2).getUrl();
        }

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getUrl() {
        return url;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Medium> getMedia() {
        return media;
    }

}
