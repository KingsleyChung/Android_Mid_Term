package com.example.kings.mid_term_project.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by weimumu on 2017/11/21.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Person.db";
    private static final int DATABASE_VERSION = 1;
    private static MySQLiteHelper dbHelper;

    private final String createTb="CREATE TABLE Person (name TEXT PRIMARY KEY, sex TEXT, category TEXT, time TEXT, description TEXT, avatar BLOB)";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MySQLiteHelper getInstance(Context context) {
        if (dbHelper == null) { //单例模式
            dbHelper = new MySQLiteHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
