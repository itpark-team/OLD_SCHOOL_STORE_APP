package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableUsers
{
    private DbHelper dbHelper;

    public TableUsers(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<User> getAll()
    {
        ArrayList<User> users = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `TableUser`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            User user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );

            users.add(user);
        }

        cursor.close();
        dbHelper.close();

        return users;
    }
}
