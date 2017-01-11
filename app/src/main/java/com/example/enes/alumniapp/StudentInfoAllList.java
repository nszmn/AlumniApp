package com.example.enes.alumniapp;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enes.alumniapp.Operations.AdminOperations;
import com.example.enes.alumniapp.Student.students;
import com.example.enes.alumniapp.model.admin;

public class StudentInfoAllList extends AppCompatActivity {

    TextView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info_all_list);

        list=(TextView)findViewById(R.id.text_AllList);
        try{
            //String uName= username.getText().toString();

            students obj= new students(getApplicationContext());


            //obj.setUsername(uName);
            obj.list_allStudents(list);

            Toast.makeText(StudentInfoAllList.this,"OK",Toast.LENGTH_SHORT).show();
            //Intent reg = new Intent(AdminRegistration.this, MainActivity.class);
            //startActivity(reg);
        }catch (SQLException e){
            e.printStackTrace();
            Toast.makeText(StudentInfoAllList.this,"hata",Toast.LENGTH_SHORT).show();

        }
    }
}
