package com.example.recomm;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("itemId")
    private int itemId;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("coverSmallUrl ")
    private String coverSmallUrl ;
    @SerializedName("coverLargeUrl ")
    private String coverLargeUrl ;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverSmallUrl() {
        return coverSmallUrl;
    }

    public void setCoverSmallUrl(String coverSmallUrl) {
        this.coverSmallUrl = coverSmallUrl;
    }

    public String getCoverLargeUrl() {
        return coverLargeUrl;
    }

    public void setCoverLargeUrl(String coverLargeUrl) {
        this.coverLargeUrl = coverLargeUrl;
    }
}
