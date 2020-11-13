package com.example.old_school_store_app.models.tables;

import com.example.old_school_store_app.models.entities.CartItem;

public class TableOrderProduct
{

    private DbHelper dbHelper;

    public TableOrderProduct(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<TableOrderProduct> getAll()
    {
        ArrayList<TableOrderProduct> tableOrderProducts = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `CartItem`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            TableOrderProduct tableOrderProduct = new CartItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );

            tableOrderProducts.add(tableOrderProduct);
        }

        cursor.close();
        dbHelper.close();

        return tableOrderProducts;
    }
}
