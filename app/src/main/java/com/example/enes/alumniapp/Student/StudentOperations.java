package com.example.enes.alumniapp.Student;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enes.alumniapp.Database.Adapter;
import com.example.enes.alumniapp.Database.AlumniDB;
import com.example.enes.alumniapp.Database.Helper;
import com.example.enes.alumniapp.Operations.AdminOperations;
import com.example.enes.alumniapp.R;
import com.example.enes.alumniapp.StudentInfoAllList;
import com.example.enes.alumniapp.model.admin;

import java.util.List;

public class StudentOperations extends AppCompatActivity {

    EditText username;
    TextView list;
    Button deleteB;
    students controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_operations);

        username=(EditText)findViewById(R.id.edit_studentDelete);



        //controller=new AlumniDB(this,"",null,1);

        int userId = Helper.getUserID();
        List<students> data = Helper.getStudentList();

        final GridView gridView = (GridView) findViewById(R.id.gridsList);
        Adapter myListAdapter = new Adapter(StudentOperations.this,
                data);
        gridView.setAdapter(myListAdapter);

        deleteB=(Button)findViewById(R.id.btn_studentDelete);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String uName= username.getText().toString();

                    students obj= new students(getApplicationContext());


                    //obj.setUsername(uName);
                    obj.Delete(username.getText().toString());

                    Toast.makeText(StudentOperations.this,"Student Deleted.",Toast.LENGTH_SHORT).show();
                    //Intent reg = new Intent(AdminRegistration.this, MainActivity.class);
                    //startActivity(reg);
                }catch (SQLException e){
                    e.printStackTrace();
                    Toast.makeText(StudentOperations.this,"Student Could not Delete",Toast.LENGTH_SHORT).show();

                }
            }
        });

        final Button listB=(Button)findViewById(R.id.btn_studentList);
        listB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sList=new Intent(StudentOperations.this, StudentInfoAllList.class);
                startActivity(sList);
            }
        });

    }

}
