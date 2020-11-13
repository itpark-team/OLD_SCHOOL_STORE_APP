package com.example.old_school_store_app.models.tables;

public class TablesCategory
{
    private DbHelper dbHelper;

    public TablesCategory(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<TablesCategory> getAll()
    {
        ArrayList<TablesCategory> tablesCategorys = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `TablesCategory`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            TablesCategory tablesCategory = new TablesCategory(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );

            tablesCategorys.add(tablesCategory);
        }

        cursor.close();
        dbHelper.close();

        return tablesCategorys;
    }
}
