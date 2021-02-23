package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.entities.OrderProduct;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableOrdersProducts
{

    private DbHelper dbHelper;

    public TableOrdersProducts(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<OrderProduct> getAll()
    {
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `orders_products`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            OrderProduct orderProduct = new OrderProduct(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );

            orderProducts.add(orderProduct);
        }

        cursor.close();
        dbHelper.close();

        return orderProducts;
    }

    public ArrayList<OrderProduct> getByOrderId(int orderId)
    {
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `orders_products` WHERE order_id = " + orderId;

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            OrderProduct orderProduct = new OrderProduct(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );

            orderProducts.add(orderProduct);
        }

        cursor.close();
        dbHelper.close();

        return orderProducts;
    }

    public void AddNewOrder(int order_id, ArrayList<CartItem> products){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 0; i < products.size(); i++)
        {
            CartItem currentProduct = products.get(i);
            db.execSQL("INSERT INTO orders_products(order_id, order_product_id, count_product) VALUES("+order_id+","+currentProduct.getProductId()+","+currentProduct.getCountProducts()+")");
        }

        dbHelper.close();
    }
}
