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


// admin table silince calisiyorsa sqliteopenHelper i sil
public class AlumniDB extends SQLiteOpenHelper {


        //public  AlumniDB(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
            //super(context,"Test.db",factory,version);
    //    super(context,"AlumniDb",null,1);

    //}


    //COLUMNS
    public static final String ROW_ID="id";
    public static final String NAME="firstname";
    public static final String SURNAME="lastname";

    //DB
    public static final String DATABASE_NAME="AlumniDB";
    public static final String TB_NAME="ogrenciler";
    public static final int DB_VERSION=1;

    //CREATE TB
   // public static final String CREATE_TB="CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT,"
      //      + "firstname TEXT NOT NULL,lastname TEXT);";

    //DROP TB
    public static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;


    public AlumniDB(Context context) {

        super(context,"AlumniDB",null,1);}

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql="CREATE TABLE admin (Id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,password TEXT)";
        db.execSQL(sql);
        String student="CREATE TABLE ogrenciler(id INTEGER PRIMARY KEY AUTOINCREMENT,"
               + "firstname TEXT NOT NULL,lastname TEXT);";
        db.execSQL(student);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST ogrenciler");
       // db.execSQL("DROP TABLE IF EXIST STUDENTS");
//        onCreate(db);

        //db.execSQL(DROP_TB);
        //onCreate(db);
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


    //For FACULTY TABLE

    public static class DbAdapter {


        static final String ROWID="Id";
        static final String NAME="faculty";

        static final String TAG="DBSpinner";

        static final String TBNAME="faculty_TB";

        public static final String DATABASE_NAME="AlumniDB";
        static final int DBVERSION='1';

        // CREATE TABLE
        static final String CREATE_TABLE="CREATE TABLE faculty_TB(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "faculty TEXT NOT NULL);";

        Context context;
        SQLiteDatabase db;
        DBHelper helper;


        public DbAdapter(Context ctx){
            this.context=ctx;
            helper=new DBHelper(context);
        }

        private class DBHelper extends SQLiteOpenHelper {

            public DBHelper(Context context){
                super(context,DATABASE_NAME,null,DBVERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL(CREATE_TABLE);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                Log.w(TAG,"Upgrading DB");
                db.execSQL("DROP TABLE IF EXISTS faculty_TB");
                onCreate(db);
            }
            public void delete_faculty(String faculty){
                this.getWritableDatabase().delete("faculty_TB","faculty='"+faculty+"'",null);
            }
        }
        //OPEN THE DB
        public DbAdapter openDB(){
            try{
                db=helper.getWritableDatabase();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return this;
        }

        //CLOSE THE DB
        public void closeDB(){helper.close();}

        //INSERT INTO TABLE Faculty
        public long add(String faculty){
            try{
                ContentValues values=new ContentValues();
                values.put(NAME,faculty);
                return db.insert(TBNAME,ROWID,values);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        }

        //Get ALL
        public Cursor getAllFaculty(){
            String[] col={ROWID,NAME};
            return db.query(TBNAME,col,null,null,null,null,null);
        }

    }

    //DEPARTMENT TABLE
    public static class DbAdapterDepartment {

        //FACULTY TABLE
        static final String ROWID="Id";

        static final String NAME="department";
       // static final String FACULTYID= ("facultyID");
        //static final String POSITION="position";
        static final String TAG="DBSpinner";

        static final String TBNAME="department_TB";

        public static final String DATABASE_NAME="AlumniDB";
        static final int DBVERSION='1';

        // CREATE TABLE
        static final String CREATE_TABLE="CREATE TABLE department_TB(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "department TEXT NOT NULL);";

        Context context;
        SQLiteDatabase db;
        DBHelper helper;

        public DbAdapterDepartment(Context ctx){
            this.context=ctx;
            helper=new DBHelper(context);
        }

        private class DBHelper extends SQLiteOpenHelper {

            public DBHelper(Context context){
                super(context,DATABASE_NAME,null,DBVERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL(CREATE_TABLE);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                Log.w(TAG,"Upgrading DB");
                db.execSQL("DROP TABLE IF EXISTS department_TB");
                onCreate(db);
            }
            public void delete_department(String department){
                this.getWritableDatabase().delete("department_TB","department='"+department+"'",null);
            }
            public final void list_department(TextView textView){
                Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM department_TB",null);
                textView.setText("");
                while (cursor.moveToNext()){
                    textView.append(cursor.getString(1)+cursor.getString(2)+"\n");
                }
            }
        }
        //OPEN THE DB
        public DbAdapterDepartment openDB(){
            try{
                db=helper.getWritableDatabase();

            }catch (SQLException e){
                e.printStackTrace();
            }
            return this;
        }

        //CLOSE THE DB
        public void closeDB(){helper.close();}

        //INSERT INTO TABLE department
        public long add(String department){
            try{
                ContentValues values=new ContentValues();
                values.put(NAME,department);
                //values.put(FACULTYID,facultyID);
                return db.insert(TBNAME,ROWID,values);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        }

        //Get ALL
        public final Cursor getAllDepartment(){
            String[] col={ROWID,NAME};
            return db.query(TBNAME,col,null,null,null,null,null);
        }
    }

}