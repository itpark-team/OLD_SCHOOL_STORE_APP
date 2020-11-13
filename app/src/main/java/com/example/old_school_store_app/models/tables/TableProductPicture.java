package com.example.old_school_store_app.models.tables;

import com.example.old_school_store_app.models.entities.TableProductPicture;

public class TableProductPicture
{

    private DbHelper dbHelper;

    public TableProductPicture(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<TableProductPicture> getAll()
    {
        ArrayList<TableProductPicture> tableProductPictures = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `TableProductPicture`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            TableProductPicture tableProductPicture = new TableProductPicture(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );

            tableProductPictures.add(tableProductPicture);
        }

        cursor.close();
        dbHelper.close();

        return tableProductPictures;
    }
}
