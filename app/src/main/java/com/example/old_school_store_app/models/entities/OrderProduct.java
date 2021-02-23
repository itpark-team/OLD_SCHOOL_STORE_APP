package com.example.old_school_store_app.models.entities;

public class OrderProduct
{
    private int orderId;
    private int orderProductId;
    private int countProduct;

    public OrderProduct(int orderId, int orderProductId, int countProduct) {
        this.orderId = orderId;
        this.orderProductId = orderProductId;
        this.countProduct = countProduct;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public int getCountProduct() {
        return countProduct;
    }
}
