package com.example.sjors.sjors_witteveen_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firstdb.db";
    private static final int DATABASE_VERSION = 1;

    String to_do_items = "to_do_items";
    String _id = "_id";
    String to_do_text = "to_do_text";
    String checked = "checked";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE to_do_items ( _id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "to_do_text TEXT, checked BOOLEAN )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS to_do_items";
        db.execSQL(query);

        onCreate(db);
    }

    public void create(ToDoItem toDoItem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(to_do_text, toDoItem.text);
        values.put(checked, toDoItem.checked);

        db.insert(to_do_items, null, values);
        db.close();
    }

    public ArrayList<String> read() {
        ArrayList<String> toDoItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT to_do_text FROM to_do_items";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String to_do_text_string = cursor.getString(cursor.getColumnIndex(to_do_text));
            toDoItems.add(to_do_text_string);
        }

        cursor.close();
        db.close();

        return toDoItems;
    }

    public void update(ToDoItem toDoItem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(checked, toDoItem.checked);

        db.update(to_do_items, values, _id + " = ?", new String[] {String.valueOf(_id)});

        db.close();
    }

    public void delete(String to_do_text_string) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(to_do_items, to_do_text + " = ?", new String[] {to_do_text_string});
        db.close();
    }

}
