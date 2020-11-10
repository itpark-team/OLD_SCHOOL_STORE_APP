package com.example.old_school_store_app.models.entities;

public class CartItem
{
    private int userId;
    private int productId;
    private int countProducts;

    public CartItem(int userId, int productId, int countProducts) {
        this.userId = userId;
        this.productId = productId;
        this.countProducts = countProducts;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getCountProducts() {
        return countProducts;
    }
}
