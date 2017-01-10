package com.example.enes.alumniapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.CustomAdapter;
import com.example.enes.alumniapp.Database.DatabaseAdapter;
import com.example.enes.alumniapp.Database.DatabaseHelper;
import com.example.enes.alumniapp.Database.Planet;
import com.example.enes.alumniapp.Student.StudentOperations;
import com.example.enes.alumniapp.model.students;

import java.util.ArrayList;

public class AddStudents extends AppCompatActivity {

    Button studentOperations,save;
    EditText number,name,surname,birthday,place,mail,time,phone,address;
    ListView lv;
    CustomAdapter adapter;
    ArrayList<Planet> planets=new ArrayList<>();
    DatabaseHelper controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudents);


        number=(EditText)findViewById(R.id.edit_StudentNumber);

        name=(EditText)findViewById(R.id.edit_Name);

        //controller=new DatabaseHelper(this,"",null,1);
        surname=(EditText)findViewById(R.id.edit_Surname);
        birthday=(EditText)findViewById(R.id.edit_Birthday);
        place=(EditText)findViewById(R.id.edit_place);
        mail=(EditText)findViewById(R.id.edit_Email);
        phone=(EditText)findViewById(R.id.edit_phone);
        time=(EditText)findViewById(R.id.edit_addTime);
        address=(EditText)findViewById(R.id.edit_address);
        final Button Add=(Button)findViewById(R.id.btnSave);


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //save(name.getText().toString(),surname.getText().toString());

                    String Name=name.getText().toString();
                    //Integer Id= number.getInputType();
                    String last=surname.getText().toString();
                    students stu=new students(getApplicationContext());

                   // stu.setID(Id);
                    stu.setFirstname(Name);
                    stu.setLastname(last);

                    stu.Insert();

                    Toast.makeText(AddStudents.this,"OK..",Toast.LENGTH_LONG).show();
                }catch (Exception e){

                    Toast.makeText(AddStudents.this,"hata",Toast.LENGTH_SHORT).show();
                }
            }
        });




        //studentOperations=(Button)findViewById(R.id.btn_studentOperations);

       // studentOperations.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View v) {
        //        Intent inn=new Intent(AddStudents.this,StudentOperations.class);
        //        startActivity(inn);
       //     }
        //});

    }
    private void save(String namef,String lastname)
    {
        DatabaseAdapter db=new DatabaseAdapter(this);
        db.openDB();
        if(db.add(namef,lastname))
        {
            name.setText("");
            surname.setText("");
        }else {
            Toast.makeText(this,"Unable To Save",Toast.LENGTH_SHORT).show();
        }

        db.closeDB();

        this.getPlanets(null);
    }

    private void getPlanets(String searchTerm)
    {
        planets.clear();

        DatabaseAdapter db=new DatabaseAdapter(this);
        db.openDB();
        Planet p=null;
        Cursor c=db.retrieve(searchTerm);
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);

            p=new Planet();
            p.setId(id);
            p.setName(name);

            planets.add(p);
        }

        db.closeDB();

        lv.setAdapter(adapter);

    }



}
