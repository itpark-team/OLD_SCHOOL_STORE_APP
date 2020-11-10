package com.example.old_school_store_app.models.entities;

public class ProductPicture
{
    private int id;
    private int productId;
    private String picturePath;


    public ProductPicture(int id, int productId, String picturePath) {
        this.id = id;
        this.productId = productId;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
