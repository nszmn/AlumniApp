package com.example.enes.alumniapp.Student;

/**
 * Created by Enes on 6.1.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.example.enes.alumniapp.Database.AlumniDB;
import com.example.enes.alumniapp.Database.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class students extends AlumniDB{
    private Context context;
    public students(Context context) {
        super(context);
    }

    private Integer ID;
    private Integer studentID;
    private String firstname;
    private String lastname;
    private Integer birthday;
    private String place;
    private Integer phone;
    private String mail;
    private String addres;
    private String regTime;
    private String sfaculty;
    private String sdepartment;

    public Integer getID(){return ID;}// Helper.getUserID()
    public void setID(Integer id){this.ID=id;}

    public Integer getStudentID(){return studentID;}
    public void setStudentID(Integer studentID){this.studentID=studentID;}

    public String getFirstname(){return firstname;}
    public void setFirstname(String firstname){ this.firstname=firstname; }

    public String getLastname(){return lastname;}
    public void setLastname(String lastname){this.lastname=lastname;}

    public Integer getBirthday(){return birthday;}
    public void setBirthday(Integer birthday){this.birthday=birthday;}

    public String getPlace(){return place;}
    public void setPlace(String place){this.place=place;}

    public Integer getPhone(){return phone;}
    public void setPhone(Integer phone){this.phone=phone;}

    public String getMail(){return  mail;}
    public void setMail(String mail){this.mail=mail;}

    public String getAddres(){return addres;}
    public void setAddres(String addres){this.addres=addres;}

    public String getRegTime(){return regTime;}
    public void setRegTime(String regTime){this.regTime=regTime;}

    public String getSfaculty(){return sfaculty;}
    public void setSfaculty(String sf){this.sfaculty=sf;}

    public String getSdepartment(){return sdepartment;}
    public void setSdepartment(String sd){this.sdepartment=sd;}

    //INSERT INTO TABLE STUDENT
    public void Insert(){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues values=new ContentValues();
            //values.put("ID",this.getID());

            values.put("id",this.getID());
            values.put("SID",this.getStudentID());
            values.put("firstname",this.getFirstname());
            values.put("lastname",this.getLastname());
            values.put("birthday",this.getBirthday());
            values.put("place",this.getPlace());
            values.put("phone",this.getPhone());
            values.put("adres",this.getAddres());
            values.put("mail",this.getMail());
            Date date = new Date();
            values.put("register",date.toString());
            values.put("sfaculty",this.getSfaculty());
           // values.put("sdepartment",this.getSdepartment());


//            this.getWritableDatabase().insertOrThrow(AlumniDB.CREATE_TB,"",values);//add database
            db.insert("ogrenciler","AlumniDB",values);
            db.close();
        }catch (SQLException e){
            e.printStackTrace();
            //Toast.makeText(students.this,"Hata",Toast.LENGTH_SHORT).show();
        }

    }

    private String TABLE_NAME="ogrenciler";

    //delete all in student table
    public void Clean(){
        String query = "Delete from student";
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("ogrenciler",null,null);
    }


    public List<students> ListAll(){
        List<students> list=new ArrayList<students>();

        String select="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor curso=db.rawQuery(select,null);

        String[] strings=null;
        while (curso.moveToNext()){
            students st=new students(context);
            st.setStudentID(curso.getInt(0));
            st.setFirstname(curso.getString(1));
            st.setLastname(curso.getString(2));
            list.add(st);
        }
        return list;
    }
    public  void list_allStudents(TextView textView){
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM ogrenciler",null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(0)+" "+ cursor.getString(1)+" "+ cursor.getString(2)+" "+ cursor.getString(3)+"\n");
        }
    }

    public void update_students(String old_username,String new_username){
        this.getWritableDatabase().execSQL("UPDATE ogrenciler SET firstname='"+new_username+"' WHERE firstname='"+old_username+"'");

    }

    public students getAllStudents(){
        String SelectAll="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(SelectAll,null);
        if(cursor.moveToFirst()){
            do{
                this.setStudentID(cursor.getInt(0));
                this.setFirstname(cursor.getString(1));
                this.setLastname(cursor.getString(2));
            }while (cursor.moveToNext());

        }
        cursor.close();
        return this;
    }
    public void Delete(String username){

        String query="Delete from ogrenciler where="+username;
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("ogrenciler",null,null);
    }

}
