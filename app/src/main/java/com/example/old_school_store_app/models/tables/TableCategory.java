package com.example.old_school_store_app.models.tables;

public class TableCategory
{
    private DbHelper dbHelper;

    public TableCategory(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<TableCategory> getAll()
    {
        ArrayList<TableCategory> tablesCategorys = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `TablesCategory`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            TableCategory tableCategory = new TableCategory(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );

            tablesCategorys.add(tableCategory);
        }

        cursor.close();
        dbHelper.close();

        return tablesCategorys;
    }
}
