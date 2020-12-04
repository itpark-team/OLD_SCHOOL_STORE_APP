package com.example.old_school_store_app.models;

import android.content.Context;

import com.example.old_school_store_app.models.tables.TableCart;
import com.example.old_school_store_app.models.tables.TableCategories;
import com.example.old_school_store_app.models.tables.TableOrders;
import com.example.old_school_store_app.models.tables.TableOrdersProducts;
import com.example.old_school_store_app.models.tables.TableProducts;
import com.example.old_school_store_app.models.tables.TableProductsPictures;
import com.example.old_school_store_app.models.tables.TableUsers;
import com.example.old_school_store_app.models.tools.DbHelper;

public class DbManager
{
    private static DbManager instance = null;

    public static DbManager GetInstance(Context context)
    {
        if (instance==null)
        {
            instance = new DbManager(context);
        }
        return instance;
    }

    private TableCart tableCartItem;
    private TableCategories tableCategories;
    private TableOrders tableOrders;
    private TableOrdersProducts tableOrdersProducts;
    private TableProducts tableProducts;
    private TableProductsPictures tableProductsPictures;
    private TableUsers tableUsers;


    private DbManager(Context context)
    {
        DbHelper dbHelper = new DbHelper(context);

        tableCartItem = new TableCart(dbHelper);
        tableCategories = new TableCategories(dbHelper);
        tableOrders = new TableOrders(dbHelper);
        tableOrdersProducts = new TableOrdersProducts(dbHelper);
        tableProducts = new TableProducts(dbHelper);
        tableProductsPictures = new TableProductsPictures(dbHelper);
        tableUsers = new TableUsers(dbHelper);

    }

    public TableCart GetTableCartItem() {
        return tableCartItem;
    }

    public TableCategories GetTableCategories() {
        return tableCategories;
    }

    public TableOrders GetTableOrders() {
        return tableOrders;
    }

    public TableOrdersProducts GetTableOrdersProducts() {
        return tableOrdersProducts;
    }

    public TableProducts GetTableProducts() {
        return tableProducts;
    }

    public TableProductsPictures GetTableProductsPictures() {
        return tableProductsPictures;
    }

    public TableUsers GetTableUsers() {
        return tableUsers;
    }

}
