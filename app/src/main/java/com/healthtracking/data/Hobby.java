package com.healthtracking.data;

/**
 * Created by larisa on 6/11/17.
 */

public class Hobby {
    private int id;
    private String name;
    private int imageDrawableId;

    public Hobby(int id, String name, int imageDrawableId) {
        this.id = id;
        this.name = name;
        this.imageDrawableId = imageDrawableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageDrawableId() {
        return imageDrawableId;
    }

    public void setImagePath(int imagePath) {
        this.imageDrawableId = imagePath;
    }
}
