package com.example.enes.alumniapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.enes.alumniapp.Database.DatabaseHelper;

/**
 * Created by Enes on 8.1.2017.
 */

public class admin extends DatabaseHelper {

    public admin(Context context) {
        super(context);
    }

    private String username;
    private String password;
    private Integer Id;

    public Integer getId(){return Id;}
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
        db.insert("admin",DATABASE_NAME,values);
        db.close();
    }
    //DELETE ADMIN
    public void Delete(){
        String query="Delete from admin";
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("admin",null,null);
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
}
