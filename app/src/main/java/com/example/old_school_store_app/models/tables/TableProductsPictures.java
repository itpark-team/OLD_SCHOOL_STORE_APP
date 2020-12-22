package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.ProductPicture;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableProductsPictures
{

    private DbHelper dbHelper;

    public TableProductsPictures(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<ProductPicture> GetAll()
    {
        ArrayList<ProductPicture> productPictures = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `products_pictures`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            ProductPicture productPicture = new ProductPicture(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );

            productPictures.add(productPicture);
        }

        cursor.close();
        dbHelper.close();

        return productPictures;
    }

    public ProductPicture GetMainProductPicture(int productId)
    {
        ProductPicture productPicture = null;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM products_pictures WHERE product_id="+productId+" LIMIT 1";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        if (cursor.moveToNext() == true)
        {
            productPicture = new ProductPicture(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );
        }

        cursor.close();
        dbHelper.close();

        return productPicture;
    }

    public ArrayList<ProductPicture> GetProductPictures(int productId)
    {
        ArrayList<ProductPicture> productPictures = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM products_pictures WHERE product_id="+productId;

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            ProductPicture productPicture = new ProductPicture(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );

            productPictures.add(productPicture);
        }

        cursor.close();
        dbHelper.close();

        return productPictures;
    }
}
