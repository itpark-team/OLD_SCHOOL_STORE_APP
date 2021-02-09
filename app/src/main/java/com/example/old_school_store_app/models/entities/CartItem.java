package com.example.old_school_store_app.models.entities;

public class CartItem
{
    private int userId;
    private int productId;
    private int countProducts;

    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void PlusCountProducts()
    {
        if(countProducts<product.getCountLeft())
        {
            countProducts++;
        }
    }

    public void MinusCountProducts()
    {
        if(countProducts>1)
        {
            countProducts--;
        }
    }
}
