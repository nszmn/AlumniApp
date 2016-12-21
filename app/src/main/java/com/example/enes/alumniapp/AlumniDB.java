package com.example.enes.alumniapp;


import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.telecom.Connection;

public class AlumniDB extends SQLiteOpenHelper {
    private Connection AlumniDB;
    private static final String AlumniDb ="AlumniDatabase";
    private static final int SURUM = 1;

    public  AlumniDB(Context cont){
        super (cont,AlumniDb,null,SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE ADMIN(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT);");
        db.execSQL("CREATE TABLE FACULTY(ID INTEGER PRIMARY KEY AUTOINCREMENT,FACULTYNAME TEXT);");
        db.execSQL("CREATE TABLE DEPARTMENT(ID INTEGER PRIMARY KEY AUTOINCREMENT,DEPARTMENTNAME TEXT,FACULTYID INTEGER, FOREIGN KEY(FACULTYID) REFERENCES FACULTY(ID));");
        db.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENTNUMBER INTEGER, FIRSTNAME TEXT, LASTNAME TEXT, BIRTHDAY INTEGER,PLACEOFBIRTH TEXT, EMAILADDRESS TEXT, ADDRESS TEXT,PHONENUMBER INTEGER,REGISTRATIONTIME INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST ADMIN");
        db.execSQL("DROP TABLE IF EXIST FACULTY");
        db.execSQL("DROP TABLE IF EXIST DEPARTMENT");
        db.execSQL("DROP TABLE IF EXIST STUDENTS");
        onCreate(db);
    }
}
