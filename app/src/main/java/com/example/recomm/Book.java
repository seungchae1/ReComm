package com.example.recomm;

import com.google.gson.annotations.SerializedName;

public class Book {
    private int itemId;
    private String title;
    private String description;
    private String coverLargeUrl ;
    private String author;
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public String getCoverLargeUrl() {
        return coverLargeUrl;
    }

    public void setCoverLargeUrl(String coverLargeUrl) {
        this.coverLargeUrl = coverLargeUrl;
    }
}
