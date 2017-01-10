package com.example.enes.alumniapp.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.AlumniDB;
import com.example.enes.alumniapp.R;

public class StudentOperations extends AppCompatActivity {

    EditText delete;
    TextView list;
    AlumniDB controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_operations);

        delete=(EditText)findViewById(R.id.edit_studentDelete);

        list=(TextView)findViewById(R.id.text_studentsList);

        //controller=new AlumniDB(this,"",null,1);

    }
    public void onclick(View view){

        switch (view.getId()){
            case R.id.btn_studentDelete:
               // controller.delete_student(delete.getText().toString());
                Toast.makeText(StudentOperations.this,"Student Deleted.",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_studentUpdate:
                //eklencek popup veya yeni sayfa
                break;
            case R.id.btn_studentList:
                //controller.list_allstudents(list);
                break;
        }
    }
}