package com.example.enes.alumniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Spinner;

import com.example.enes.alumniapp.Database.AlumniDB;

public class AddFaculty extends AppCompatActivity {

    EditText addFaculty;
    TextView list;
    AlumniDB controller;
    Spinner sp;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        addFaculty=(EditText)findViewById(R.id.edit_faculty);

        list=(TextView)findViewById(R.id.text_list);

        //controller=new AlumniDB(this,"",null,1);

        sp=(Spinner)findViewById(R.id.spinner_faculty);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa=new Intent(AddFaculty.this,facultyAdd.class);
                startActivity(aa);
            }
        });
    }

}