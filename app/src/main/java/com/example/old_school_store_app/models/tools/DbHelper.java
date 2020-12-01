package com.example.old_school_store_app.models.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"app.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS \" products\" (\n" +
                "\t\"id\"\tINTEGER NOT NULL,\n" +
                "\t\"name\"\tTEXT NOT NULL,\n" +
                "\t\"price\"\tINTEGER NOT NULL,\n" +
                "\t\"count_purchases\"\tINTEGER NOT NULL,\n" +
                "\t\"description\"\tTEXT NOT NULL,\n" +
                "\t\"category_id\"\tINTEGER NOT NULL,\n" +
                "\t\"count_left\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

        //db.execSQL("DELETE FROM products");

        db.execSQL("INSERT INTO \" products\" (\"id\",\"name\",\"price\",\"count_purchases\",\"description\",\"category_id\",\"count_left\") VALUES (1,'трусы',0,125,'мужские',0,40);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
