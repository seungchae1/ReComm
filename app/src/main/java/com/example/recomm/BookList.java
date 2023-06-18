package com.example.recomm;

import com.example.recomm.Model.Book;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookList {
    @SerializedName("item")
    private List<Book> item;

    public List<Book> getItem() {
        return item;
    }

    public void setItem(List<Book> item) {
        this.item = item;
    }
}
