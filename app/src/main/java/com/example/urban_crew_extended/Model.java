package com.example.urban_crew_extended;

public class Model {

    private int image;
    private String title;


    public Model(int image, String title, String desc) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


