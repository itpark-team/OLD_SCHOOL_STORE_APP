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

    public User GetByLoginAndPassword(String login, String password)
    {
        User user = null;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `users` WHERE login='"+login+"' AND password='"+password+"'";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
             user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }

        cursor.close();
        dbHelper.close();

        return user;
    }

    public void InsertNewUser(User user)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "INSERT INTO `users` (login,password,name,bdate,phone,email) VALUES('"+user.getLogin()+"','"+user.getPassword()+"','"+user.getName()+"',"+user.getbDate()+",'"+user.getPhone()+"','"+user.getEmail()+"')";

        db.execSQL(sqlCommand);
    }
}
