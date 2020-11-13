package com.example.old_school_store_app.models.tables;

import com.example.old_school_store_app.models.entities.CartItem;

public class TableCartItem
{
    private DbHelper dbHelper;

    public TableCartItem(DbHelper dbHelper)
    {
       this.dbHelper = dbHelper;
    }

    public ArrayList<CartItem> getAll()
    {
        ArrayList<CartItem> cartItems = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `CartItem`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            CartItem cartItem = new CartItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );

            cartItems.add(cartItem);
        }

        cursor.close();
        dbHelper.close();

        return cartItems;
    }
}
