package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableProducts
{

    private DbHelper dbHelper;

    public TableProducts(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<Product> getAll()
    {
        ArrayList<Product> products = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `products`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            Product product = new Product(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            );

            products.add(product);
        }

        cursor.close();
        dbHelper.close();

        return products;
    }
}
