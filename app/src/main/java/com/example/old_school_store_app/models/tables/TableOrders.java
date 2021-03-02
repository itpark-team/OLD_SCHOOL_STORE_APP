package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.Order;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableOrders {

    private DbHelper dbHelper;

    public TableOrders(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<Order> GetByUserId(int UserId)
    {
        ArrayList<Order> orders = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `orders` WHERE user_id="+UserId;

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            Order order = new Order(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );

            orders.add(order);
        }

        cursor.close();
        dbHelper.close();

        return orders;
    }



    public void AddNew(int user_id,int dt, int total_price)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "INSERT INTO orders(user_id, dt, total_price) VALUES("+user_id+","+dt+","+total_price+")";

        db.execSQL(sqlCommand);

        dbHelper.close();
    }

    public int GetLastInsertId(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT max(id) FROM orders";

        Cursor cursor = db.rawQuery(sqlCommand,null);
        boolean resultInsert = cursor.moveToNext();

        int lastInsertId = cursor.getInt(0);

        cursor.close();
        dbHelper.close();

        return lastInsertId;
    }
}
