package com.example.sjors.sjors_witteveen_pset4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firstdb.db";
    private static final int DATABASE_VERSION = 1;

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

    }


}
