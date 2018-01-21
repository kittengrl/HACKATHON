package com.example.team.hackathon;

/**
 * Created by ADMIN on 1/21/2018.
 */

public class Blog {
    private String title, desc, image;
    public Blog(){

    }
    public Blog(String title, String desc, String image){
        this.title = title;
        this.desc = desc;
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
