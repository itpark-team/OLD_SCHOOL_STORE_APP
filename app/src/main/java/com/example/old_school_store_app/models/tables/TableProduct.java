package com.example.old_school_store_app.models.tables;

import com.example.old_school_store_app.models.entities.CartItem;

public class TableProduct
{

    private DbHelper dbHelper;

    public TableProduct(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<TableProduct> getAll()
    {
        ArrayList<TableProduct> tableProducts = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `TableProduct`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            TableProduct tableProduct = new TableProduct(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getInt(7)
            );

            tableProducts.add(tableProduct);
        }

        cursor.close();
        dbHelper.close();

        return tableProducts;
    }
}
