package com.example.enes.alumniapp.model;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enes.alumniapp.AlumniDB;
import com.example.enes.alumniapp.R;

public class AddDepartment extends AppCompatActivity {

    EditText departmentName;
    TextView listDepartment;
    AlumniDB controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);
        departmentName=(EditText)findViewById(R.id.edit_add_department);
        listDepartment=(TextView)findViewById(R.id.text_department_list);
        controller=new AlumniDB(this,"",null,1);
    }
    public void btn_click(View view){
        switch (view.getId()){
            case R.id.btn_add_department:
                try{
                    controller.insert_department(departmentName.getText().toString());

                }catch (SQLException e){
                    Toast.makeText(AddDepartment.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }
        }
    }

}
