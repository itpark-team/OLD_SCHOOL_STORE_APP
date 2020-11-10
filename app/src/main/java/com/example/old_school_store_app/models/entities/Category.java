package com.example.old_school_store_app.models.entities;

public class Category
{
    private int id;
    private String name;
    private String picturePath;

    public Category(int id, String name, String picturePath) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
