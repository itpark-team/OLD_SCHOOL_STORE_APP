package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableCart
{
    private DbHelper dbHelper;

    public TableCart(DbHelper dbHelper)
    {
       this.dbHelper = dbHelper;
    }

    public ArrayList<CartItem> getAll()
    {
        ArrayList<CartItem> cartItems = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `cart`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            CartItem cartItem = new CartItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );

            cartItems.add(cartItem);
        }

        cursor.close();
        dbHelper.close();

        return cartItems;
    }
}
