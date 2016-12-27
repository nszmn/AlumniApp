package com.example.enes.alumniapp;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.telecom.Connection;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class AlumniDB extends SQLiteOpenHelper {
    private Connection AlumniDB;
    private static final String AlumniDb ="AlumniDatabase";
    private static final int SURUM = 1;

    public  AlumniDB(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,"Test.db",factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE Admin(ID integer PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT UNIQUE,PASSWORD TEXT);");

        db.execSQL("CREATE TABLE FACULTY(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FACULTYNAME TEXT);");

        db.execSQL("CREATE TABLE DEPARTMENT(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DEPARTMENTNAME TEXT,FACULTYID INTEGER," +
                " FOREIGN KEY(FACULTYID) REFERENCES FACULTY(ID));");

        db.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " STUDENTNUMBER INTEGER UNIQUE," +
                " FIRSTNAME TEXT," +
                " LASTNAME TEXT," +
                " BIRTHDAY INTEGER," +
                "PLACEOFBIRTH TEXT," +
                " EMAILADDRESS TEXT," +
                " ADDRESS TEXT," +
                "PHONENUMBER INTEGER," +
                "REGISTRATIONTIME TEXT," +
                "FACULTYID INTEGER, FOREIGN KEY(FACULTYID) REFERENCES FACULTY(ID));");
               // "DEPARTMENTID INTEGER, FOREIGN KEY(DEPARTMENTID) REFERENCES DEPARTMENT(ID));");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST Admin");
        db.execSQL("DROP TABLE IF EXIST FACULTY");
        db.execSQL("DROP TABLE IF EXIST DEPARTMENT");
        db.execSQL("DROP TABLE IF EXIST STUDENTS");
        onCreate(db);
    }

    // faculty adding
    public void insert_faculty(String facultyName){
        ContentValues contentValues=new ContentValues();
        contentValues.put("FACULTYNAME",facultyName);
        this.getWritableDatabase().insertOrThrow("FACULTY","",contentValues);
    }
    public void list_faculty(TextView textView){
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM FACULTY",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+"\n");
        }
    }
    public void delete_faculty(String facultyname){
        this.getWritableDatabase().delete("FACULTY","FACULTYNAME='"+facultyname+"'",null);
    }

    //Student adding

    public  void insert_student(int number,String firstname,String lastname,String birthday,String place,String mail,String address,int phone,String time){
        ContentValues contentValues=new ContentValues();
        contentValues.put("STUDENTNUMBER",number);
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        contentValues.put("BIRTHDAY",birthday);
        contentValues.put("PLACEOFBIRTH",place);
        contentValues.put("EMAILADDRESS",mail);
        contentValues.put("ADDRESS",address);
        contentValues.put("PHONENUMBER",phone);
        contentValues.put("REGISTRATIONTIME",time);
        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);
    }
    public void delete_student(String firstname){
        this.getWritableDatabase().delete("STUDENTS","FIRSTNAME='"+firstname+"'",null);
    }
    public void update_students(String old_firstname,String new_firstname){
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+new_firstname+"' WHERE FIRSTNAME='"+old_firstname+"'");
    }
    public  void list_allstudents(TextView textView){
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+ cursor.getString(2)+"\n");
        }
    }
    //admin
    public void insert_admin(String username,String password){
        ContentValues contentValues=new ContentValues();
        contentValues.put("USERNAME",username);
        contentValues.put("PASSWORD",password);
        this.getWritableDatabase().insertOrThrow("Admin","",contentValues);
    }
    public void delete_admin(String username){
        this.getWritableDatabase().delete("Admin","USERNAME='"+username+"'",null);
    }
    public void list_admin(TextView textView){
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM ADMIN",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+"\n");
        }
    }
    //department

    public void insert_department(String departmentName){
        ContentValues contentValues=new ContentValues();
        contentValues.put("DEPARTMENTNAME",departmentName);
        this.getWritableDatabase().insertOrThrow("DEPARTMENT","",contentValues);
    }
    public void list_department(TextView textView){
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM DEPARTMENT",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+"\n");
        }
    }
    public void delete_department(String departmentname){
        this.getWritableDatabase().delete("DEPARTMENT","DEPARTMENTNAME='"+departmentname+"'",null);
    }


    public List<String> getAllFaculties()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM FACULTY"  ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<String> lectrues = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                lectrues.add(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lectrues;
    }

}
