package com.example.myapplication.beans;

public class Category {
    String categoryName;

    public Category(String heading) {
        this.categoryName = heading;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
