package com.st18apps.testvrg.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Medium {
    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    @SerializedName("approved_for_syndication")
    private int approvedForSyndication;
    @SerializedName("media-metadata")
    private List<MediaMetadatum> mediaMetadata = null;

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public List<MediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }
}
