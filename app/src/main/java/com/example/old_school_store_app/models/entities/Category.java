package com.example.old_school_store_app.models.entities;

public class Category
{
    private int id;
    private String name;
    private String description;
    private String picturePath;

    private int pictureId;

    public Category(int id, String name,String description, String picturePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
