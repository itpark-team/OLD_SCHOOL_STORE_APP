package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableOrder {

    private DbHelper dbHelper;

    public TableOrder(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<TableOrder> getAll()
    {
        ArrayList<TableOrder> tableOrders = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `TableOrder`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            TableOrder tableOrder = new TableOrder(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            );

            tableOrders.add(tableOrder);
        }

        cursor.close();
        dbHelper.close();

        return tableOrders;
    }
}
