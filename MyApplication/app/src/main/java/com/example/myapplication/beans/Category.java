package com.example.myapplication.beans;

public class Category {
    String heading;
    int titleImage;

    public Category(String heading, int titleImage) {
        this.heading = heading;
        this.titleImage = titleImage;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }
}
