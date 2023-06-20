package com.example.recomm.Adapter;

public class CategoryItem {

    String category;
    int resld;

    public CategoryItem(String category, int resld) {
        this.category = category;
        this.resld = resld;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getResId() {
        return resld;
    }

    public void setResId(int resId) {
        this.resld = resld;
    }
}
