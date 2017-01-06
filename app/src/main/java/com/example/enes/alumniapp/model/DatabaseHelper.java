package com.example.enes.alumniapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Enes on 5.1.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Alumni";
    public DatabaseHelper(Context context) {super(context,"Alumni",null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1="CREATE TABLE admin(Id INTEGER PRIMARY AUTOINCREMENT, username TEXT,password TEXT";
        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
