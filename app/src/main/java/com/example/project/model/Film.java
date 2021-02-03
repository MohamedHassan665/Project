package com.example.project.model;

public class Film {
    private String imageUrl;
    private String title;
    private String author;
    private String description;
    private boolean state;
    float rate;


    public Film(String imageUrl, String title, String author, boolean state) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.author = author;
        this.state=state;
    }

    public Film(String imageUrl, String title, String author, String description,boolean state,float rate) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.author = author;
        this.description = description;
        this.state=state;
        this.rate=rate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String gettitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public float getRate() {
        return rate;
    }
    public String getDescription() {
        return description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


}


