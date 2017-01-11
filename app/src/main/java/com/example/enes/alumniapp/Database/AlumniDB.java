package com.example.enes.alumniapp.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.INTEGER;


// admin table silince calisiyorsa sqliteopenHelper i sil
public class AlumniDB extends SQLiteOpenHelper  {

    //COLUMNS
    public static final String ROW_ID="id";
    public static final String STUDENTNUMBER="SID";
    public static final String NAME="firstname";
    public static final String SURNAME="lastname";
    public static final String BIRTH="birthday";
    public static final String PLACE="place";
    public static final String PHONE="phone";
    public static final String ADRES="adres";
    public static final String MAIL="mail";
    public static final String REGISTR="register";
    //public static final String SDEPARTMENT="sdepartment";
    public static final String SFACULTY="sfaculty";

    //DB
    public static final String DATABASE_NAME="AlumniDB";
    public static final String TB_NAME="ogrenciler";
    public static final int DB_VERSION=1;

    //DROP TB
    public static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;

    public AlumniDB(Context context) {

        super(context,"AlumniDB",null,1);}

   @Override
    public void onCreate(SQLiteDatabase db){
       try{
           String sql="CREATE TABLE admin (Id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,password TEXT)";
           db.execSQL(sql);

           String student="CREATE TABLE ogrenciler(id INTEGER PRIMARY KEY AUTOINCREMENT,SID INTEGER NOT NULL,"
                   + "firstname TEXT NOT NULL,lastname TEXT NOT NULL ,birthday TEXT,place TEXT,phone INTEGER,adres TEXT,mail TEXT,register INTEGER,sfaculty Text);";
           db.execSQL(student);

       }catch (SQLException e){
           e.printStackTrace();
       }
   }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST ogrenciler");
        //db.execSQL("DROP TABLE IF EXIST admin");
        //onCreate(db);

        //db.execSQL(DROP_TB);
        //onCreate(db);
    }

    public static class DBFACULTYAdapter {

        static final String ROWID="Id";
        static final String NAMEFACULTY="faculty";
        static final String TAGFACULTY="DBSpinner";
        static final String TBNAMEFACULTY="faculty_TB";
        // CREATE TABLE FACULTY
        static final String CREATE_TABLE_FACULTY="CREATE TABLE faculty_TB(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "faculty TEXT NOT NULL);";
        Context context;
        SQLiteDatabase db;
        DBFacultyHelper helper;

        public DBFACULTYAdapter (Context ctx){
          this.context=ctx;
           helper=new DBFacultyHelper(context);
         }

        private class DBFacultyHelper extends SQLiteOpenHelper {

            public DBFacultyHelper(Context context){
                super(context,DATABASE_NAME,null,1);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL(CREATE_TABLE_FACULTY);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                Log.w(TAGFACULTY,"Upgrading DB");
                db.execSQL("DROP TABLE IF EXISTS faculty_TB");
                onCreate(db);
            }
            public void delete_faculty(String faculty){
                this.getWritableDatabase().delete("faculty_TB","faculty='"+faculty+"'",null);
            }
        }
        public DBFACULTYAdapter openDB(){
            try{
                db=helper.getWritableDatabase();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return this;
        }

        //INSERT INTO TABLE Faculty
        public long addFaculty(String faculty){
            try{
                ContentValues values=new ContentValues();
                values.put("faculty",faculty);
                return db.insert("faculty_TB","Id",values);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        }

        //Get ALL FACULTY
        public Cursor getAllFaculty(){
            String[] col={ROWID,NAMEFACULTY};
            return db.query(TBNAMEFACULTY,col,null,null,null,null,null);
        }

        //CLOSE THE DB
        public void closeDB(){helper.close();}
    }


    //DEPARTMENT TABLE
    public static class DbDepartmentAdapter {
        //DEPARTMENT
        static final String ROWIDDEPARTMENT="Id";
        static final String NAMEDEPARTMENT="department";
        static final String FACULTYDEPARTMENT="faculty";
        static final String TAGDEPARTMENT="DBDepartmentSpinner";
        static final String TBNAMEDEPARTMENT="department_TB";

        // CREATE TABLE
        static final String CREATE_TABLE_DEPARTMENT="CREATE TABLE department_TB(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "department TEXT NOT NULL,faculty TEXT);";

        Context context;
        SQLiteDatabase db;
        DBDepartmentHelper helper;

        public DbDepartmentAdapter (Context ctx){
            this.context=ctx;
            helper=new DBDepartmentHelper(context);
        }

        private String facultyD;
        public String getFacultyD(){return facultyD;}
        public void setFacultyD(String facultyD){this.facultyD=facultyD;}


        private class DBDepartmentHelper extends SQLiteOpenHelper {

            public DBDepartmentHelper(Context context){
                super(context,DATABASE_NAME,null,1);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL(CREATE_TABLE_DEPARTMENT);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                Log.w(TAGDEPARTMENT,"Upgrading DB");
                db.execSQL("DROP TABLE IF EXISTS department_TB");
                onCreate(db);
            }
            public void delete_department(String department){
                this.getWritableDatabase().delete("department_TB","department='"+department+"'",null);
            }
        }

        //OPEN THE DATABASE
        public DbDepartmentAdapter openDB(){
            try{
                db=helper.getWritableDatabase();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return this;
        }



        //INSERT INTO TABLE Department
        public long addDepartment(String department,String facultyD){
            try{
                ContentValues values=new ContentValues();
                values.put(NAMEDEPARTMENT,department);
                values.put(FACULTYDEPARTMENT,facultyD);
                return db.insert(TBNAMEDEPARTMENT,ROWIDDEPARTMENT,values);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        }

        //Get ALL DEPARTMENT
        public Cursor getAllDepartment(){
            String[] col={ROWIDDEPARTMENT,NAMEDEPARTMENT};
            return db.query(TBNAMEDEPARTMENT,col,null,null,null,null,null);
        }
        //CLOSE THE DB
        public void closeDB(){helper.close();}
    }
}