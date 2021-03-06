package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.tools.DbHelper;

public class TableSettingsApp
{
    private DbHelper dbHelper;

    public TableSettingsApp(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public void Add(String key , String value)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "INSERT INTO `settings_app`(key_field, value_field) VALUES('"+key+"','"+value+"')";
        db.execSQL(sqlCommand);

        dbHelper.close();
    }

    public void Delete(String key)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "DELETE FROM `settings_app` WHERE key_field='"+key+"'";
        db.execSQL(sqlCommand);

        dbHelper.close();
    }

    public void Update(String key, String value)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "UPDATE settings_app SET value_field='"+value+"' WHERE key_field='"+key+"'";
        db.execSQL(sqlCommand);

        dbHelper.close();
    }

    public String Get(String key)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `settings_app` WHERE key_field='"+key+"'";

        Cursor cursor = db.rawQuery(sqlCommand,null);
        boolean result = cursor.moveToNext();

        String value = cursor.getString(2);

        cursor.close();
        dbHelper.close();

        return value;
    }

    public boolean ExistKey(String key)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `settings_app` WHERE key_field='"+key+"'";

        Cursor cursor = db.rawQuery(sqlCommand,null);
        boolean exist = cursor.moveToNext();

        cursor.close();
        dbHelper.close();

        return exist;
    }
}
