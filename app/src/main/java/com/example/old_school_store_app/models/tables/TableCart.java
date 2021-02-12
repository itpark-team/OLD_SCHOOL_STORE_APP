package com.example.old_school_store_app.models.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class TableCart
{
    private DbHelper dbHelper;

    public TableCart(DbHelper dbHelper)
    {
       this.dbHelper = dbHelper;
    }

    public ArrayList<CartItem> GetRecordsByUser(int userId)
    {
        ArrayList<CartItem> cartItems = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM cart WHERE user_id="+userId;

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

    public void UpdateCartProductCountByProductIdAndUserId(int userId, int productId, int productCount)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "UPDATE cart SET count_product="+productCount+" WHERE user_id="+userId+" AND product_id="+productId;

        db.execSQL(sqlCommand);

        dbHelper.close();
    }

    public void AddToCart(CartItem cartItem)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM cart WHERE user_id="+cartItem.getUserId()+" AND product_id="+cartItem.getProductId();

        Cursor cursor = db.rawQuery(sqlCommand,null);
        boolean exist = cursor.moveToNext();

        cursor.close();

        if(exist) // сделать проверку на наличие предмета в списке
        {
            db.execSQL("UPDATE cart SET count_product=count_product+"+cartItem.getCountProducts()+" WHERE user_id="+cartItem.getUserId()+" AND product_id="+cartItem.getProductId());
            //Сделать нормальное добавление к существующему
        }
        else
        {
            db.execSQL("INSERT INTO cart(user_id, product_id, count_product) VALUES("+cartItem.getUserId()+","+cartItem.getProductId()+","+cartItem.getCountProducts()+")");
        }
        dbHelper.close();
    }

    public void ClearCartByUser(int userId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("DELETE FROM cart WHERE user_id="+userId);

        dbHelper.close();
    }

    public void DeleteCartItemByProductIdAndUserId(int userId, int productId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "DELETE FROM cart WHERE user_id = "+userId+" AND product_id = "+productId;

        db.execSQL(sqlCommand);

        dbHelper.close();
    }
}
