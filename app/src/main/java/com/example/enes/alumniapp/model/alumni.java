package com.example.enes.alumniapp.model;

import com.example.enes.alumniapp.AlumniDB;

/**
 * Created by Enes on 5.1.2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class alumni extends AlumniDB {
    private String username;
    private String password;
    private Integer Id;


    public alumni(Context context) {
        super(context);
    }

    public Integer getId(){return Id;}
    public void setId(Integer id){this.Id=id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
    public alumni Login(String username,String password){
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
