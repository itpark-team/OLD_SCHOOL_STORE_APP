package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.CartItem;
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

    public ArrayList<Product> GetAll()
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

    public ArrayList<Product> GetByPartOfName(String partOfName)
    {
        ArrayList<Product> products = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `products` WHERE name like '%"+partOfName+"%'";

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

    public ArrayList<Product> GetByCategoryId(int categoryId)
    {
        ArrayList<Product> products = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `products` WHERE category_id="+categoryId;

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

    public Product GetById(int productId)
    {
        Product product = null;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `products` WHERE id="+productId;

        Cursor cursor = db.rawQuery(sqlCommand,null);

        if (cursor.moveToNext() == true)
        {
             product = new Product(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            );
        }

        cursor.close();
        dbHelper.close();

        return product;
    }

    public void RecalculateCountProducts(ArrayList<CartItem> products){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 0; i < products.size(); i++)
        {
            CartItem currentProduct = products.get(i);

            db.execSQL("UPDATE `products` SET count_left=count_left-"+currentProduct.getCountProducts()+" WHERE id="+currentProduct.getProductId());
        }

        dbHelper.close();
    }
}
