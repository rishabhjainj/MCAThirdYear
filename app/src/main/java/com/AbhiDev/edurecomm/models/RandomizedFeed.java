package com.AbhiDev.edurecomm.models;

public class RandomizedFeed {

    private String headline, text;
    private String image;
    private String url;

    public RandomizedFeed(String heading, String text, String image,String url) {
        this.headline = heading;
        this.text = text;
        this.image = image;
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public String getUrl(){
        return url;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }
}
