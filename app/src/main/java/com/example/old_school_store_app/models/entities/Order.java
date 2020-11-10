package com.example.old_school_store_app.models.entities;

public class Order
{
    private int id;
    private int userId;
    private int dt;
    private int totalPrice;

    public Order(int id, int userId, int dt, int totalPrice) {
        this.id = id;
        this.userId = userId;
        this.dt = dt;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getDt() {
        return dt;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
