package com.example.old_school_store_app.models.tables;

public class TableUser
{
    private DbHelper dbHelper;

    public TableUser(DbHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public ArrayList<TableUser> getAll()
    {
        ArrayList<TableUser> tableUsers = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sqlCommand = "SELECT * FROM `TableUser`";

        Cursor cursor = db.rawQuery(sqlCommand,null);

        while (cursor.moveToNext() == true)
        {
            TableUser tableUser = new TableUser(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );

            tableUsers.add(tableUser);
        }

        cursor.close();
        dbHelper.close();

        return tableUsers;
    }
}
