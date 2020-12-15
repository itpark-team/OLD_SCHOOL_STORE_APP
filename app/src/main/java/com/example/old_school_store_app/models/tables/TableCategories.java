package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.Category;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableCategories
{
    private DbHelper dbHelper;

    public TableCategories(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<Category> GetAll()
    {
        ArrayList<Category> categories = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `categories`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            Category category = new Category(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );

            categories.add(category);
        }

        cursor.close();
        dbHelper.close();

        return categories;
    }
}
