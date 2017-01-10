package com.example.enes.alumniapp.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Enes on 8.1.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="AlumniDB";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            String sql = "CREATE TABLE admin (Id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT IS NOT NULL ,password TEXT IS NOT NULL)";
            sqLiteDatabase.execSQL(sql);

            //String ogr="CREATE TABLE ogr(ID INTEGER PRIMARY KEY AUTOINCREMENT,firstname TEXT,lastname TEXT)";
            //sqLiteDatabase.execSQL(ogr);


            //sqLiteDatabase.execSQL(AlumniDB.CREATE_TB);

        } catch (SQLException e) {
            e.printStackTrace();
            //Toast.makeText(this,"DatabaseHelper da Hata olustu !!",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXIST admin,ogrenciler");
        //sqLiteDatabase.execSQL(AlumniDB.DROP_TB);
        onCreate(sqLiteDatabase);
    }
}
