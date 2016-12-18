package com.example.enes.alumniapp;


import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class AlumniDB extends SQLiteOpenHelper {
    private static final String AlumniDb ="AlumniDatabase";
    private static final int SURUM = 1;

    public  AlumniDB(Context cont){
        super (cont,AlumniDb,null,SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE Admin(Id int primary key unique Auto increment,Username nvarchar,Password nvarchar);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST Students");
        onCreate(db);
    }
}
