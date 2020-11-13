package com.example.old_school_store_app.models.tools;

import android.content.Context;

import com.example.old_school_store_app.models.tables.TableCartItem;
import com.example.old_school_store_app.models.tables.TableCategory;
import com.example.old_school_store_app.models.tables.TableOrder;
import com.example.old_school_store_app.models.tables.TableOrderProduct;
import com.example.old_school_store_app.models.tables.TableProduct;
import com.example.old_school_store_app.models.tables.TableProductPicture;
import com.example.old_school_store_app.models.tables.TableUser;

public class DbManager {
    private static DbManager instance = null;

    public static DbManager getInstance(Context context) {
        if (instance==null){
            instance = new DbManager(context);
        }
        return instance;
    }

    private TableCartItem tableCartItem;
    private TableCategory tableCategory;
    private TableOrder tableOrder;
    private TableOrderProduct tableOrderProduct;
    private TableProduct tableProduct;
    private TableProductPicture tableProductPicture;
    private TableUser tableUser;


    private DbManager(Context context){
        DbHelper dbHelper = new DbHelper(context);

        tableCartItem = new TableCartItem(dbHelper);
        tableCategory = new TableCategory(dbHelper);
        tableOrder = new TableOrder(dbHelper);
        tableOrderProduct = new TableOrderProduct(dbHelper);
        tableProduct = new TableProduct(dbHelper);
        tableProductPicture = new TableProductPicture(dbHelper);
        tableUser = new TableUser(dbHelper);

    }

    public TableCartItem getTableCartItem() {
        return tableCartItem;
    }

    public TableCategory getTableCategory() {
        return tableCategory;
    }

    public TableOrder getTableOrder() {
        return tableOrder;
    }

    public TableOrderProduct getTableOrderProduct() {
        return tableOrderProduct;
    }

    public TableProduct getTableProduct() {
        return tableProduct;
    }

    public TableProductPicture getTableProductPicture() {
        return tableProductPicture;
    }

    public TableUser getTableUser() {
        return tableUser;
    }

}
