package com.example.old_school_store_app.models.entities;

public class Product
{
    private int id;
    private String name;
    private int price;
    private int countPurchases;
    private String description;
    private int categoryId;
    private int countLeft;

    private int mainPictureId;


    public Product(int id, String name, int price, int countPurchases, String description, int categoryId, int countLeft) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.countPurchases = countPurchases;
        this.description = description;
        this.categoryId = categoryId;
        this.countLeft = countLeft;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCountPurchases() {
        return countPurchases;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getCountLeft() {
        return countLeft;
    }

    public void setMainPictureId(int mainPictureId) {
        this.mainPictureId = mainPictureId;
    }

    public int getMainPictureId() {
        return mainPictureId;
    }
}
