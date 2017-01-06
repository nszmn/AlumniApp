package com.example.enes.alumniapp;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enes.alumniapp.Operations.StudentOperations;
import com.example.enes.alumniapp.model.students;

public class AddStudents extends AppCompatActivity {
    Button studentOperations;
    EditText number,name,surname,birthday,place,mail,time,phone,address;

    AlumniDB controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudents);

        number=(EditText)findViewById(R.id.edit_StudentNumber);
        name=(EditText)findViewById(R.id.edit_Name);
        surname=(EditText)findViewById(R.id.edit_Surname);
        birthday=(EditText)findViewById(R.id.edit_Birthday);
        place=(EditText)findViewById(R.id.edit_place);
        mail=(EditText)findViewById(R.id.edit_Email);
        phone=(EditText)findViewById(R.id.edit_phone);
        time=(EditText)findViewById(R.id.edit_addTime);
        address=(EditText)findViewById(R.id.edit_address);
        final Button Add=(Button)findViewById(R.id.btn_AddStudent);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String Name=name.getText().toString();
                    Integer Id= number.getInputType();
                    String last=surname.getText().toString();
                    students stu=new students(getApplicationContext());

                    stu.setID(Id);
                    stu.setFirstname(Name);
                    stu.setLastname(last);

                    stu.Insert();

                    Toast.makeText(AddStudents.this,"OK..",Toast.LENGTH_LONG).show();
                }catch (Exception e){

                    Toast.makeText(AddStudents.this,"hata",Toast.LENGTH_SHORT).show();
                }
            }
        });

        controller=new AlumniDB(this,"",null,1);

        studentOperations=(Button)findViewById(R.id.btn_studentOperations);

        studentOperations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn=new Intent(AddStudents.this,StudentOperations.class);
                startActivity(inn);
            }
        });

    }
    public void btn_click(View view){

        switch (view.getId()){
            case R.id.btn_AddStudent:
                try{
                    controller.insert_student(number.getInputType(),name.getText().toString(),surname.getText().toString(),
                            birthday.getText().toString(),place.getText().toString(),mail.getText().toString(),phone.getText().toString(),
                            time.getInputType(),address.getText().toString());
                    Toast.makeText(AddStudents.this,"SAVED",Toast.LENGTH_SHORT).show();
                }catch (SQLException e){
                    Toast.makeText(AddStudents.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();

            }

                break;
        }
    }
}
