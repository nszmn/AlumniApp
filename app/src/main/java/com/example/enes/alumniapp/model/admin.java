package com.example.enes.alumniapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.example.enes.alumniapp.Database.AlumniDB;
import com.example.enes.alumniapp.Database.DatabaseHelper;
import com.example.enes.alumniapp.Database.Helper;

/**
 * Created by Enes on 8.1.2017.
 */

public class admin extends AlumniDB {

    public admin(Context context) {
        super(context);
    }

    private String username;
    private String password;
    private Integer Id;

    public Integer getId(){return Helper.getUserID();}
    public void setId(Integer id){this.Id=id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}



    //INSERT ADMIN
    public void Insert(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",this.getUsername());
        values.put("password",this.getPassword());
        db.insert("admin","AlumniDB",values);
        db.close();
    }
    //DELETE ADMIN
    public void Delete(String username){

        String query="Delete from admin where="+username;
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("admin",null,null);
    }


    public boolean deleteRow(long username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "username" + "=" + username;
        return db.delete("admin", where, null) != 0;
    }


    //ADMIN LOGIN

    public admin Login(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery="select * from admin where username=?";
        Cursor cursor=db.rawQuery(selectQuery,new String[]{username});
        if(cursor.moveToNext()){


            this.setId(cursor.getInt(0));
            this.setUsername(cursor.getString(1));
            this.setPassword(cursor.getString(2));

        }else
            //Toast.makeText(Login.class,"sos",Toast.LENGTH_SHORT).show();
            return null;

        close();
        return this;
    }

    public  void list_allAdmin(TextView textView){
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM admin",null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(0)+" "+ cursor.getString(1)+" "+ cursor.getString(2)+"\n");
        }
    }

}
