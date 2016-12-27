package com.example.enes.alumniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.enes.alumniapp.model.AddDepartment;

public class AdminPage extends AppCompatActivity {

    Button admin,student,faculty,department,operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_page);
        admin=(Button)findViewById(R.id.btn_admin_operation);
        student=(Button)findViewById(R.id.btn_admin_students);
        faculty=(Button)findViewById(R.id.btn_admin_faculty);
        department=(Button)findViewById(R.id.btn_admin_department);
        operation=(Button)findViewById(R.id.btn_admin_op);

        operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPage.this,AdminOperations.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(AdminPage.this,AdminRegistration.class);
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(AdminPage.this,AddStudents.class);
                startActivity(intent);
            }
        });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPage.this,AddFaculty.class);
                startActivity(intent);
            }
        });
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPage.this, AddDepartment.class);
                startActivity(intent);
            }
        });

    }
}
